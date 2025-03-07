package org.archcnl.owlify.famix.visitors;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.jena.ontology.Individual;
import org.archcnl.owlify.famix.ontology.FamixOntology;

public class ObjectCreationExpressionVisitor extends VoidVisitorAdapter<Void> {

    private FamixOntology ontology;
    private Individual typeOfCreatedObject;

    public ObjectCreationExpressionVisitor(FamixOntology ontology) {
        this.ontology = ontology;
    }

    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        DeclaredJavaTypeVisitor visitor = new DeclaredJavaTypeVisitor(ontology);
        n.accept(visitor, null);
        typeOfCreatedObject = visitor.getDeclaredType();
    }

    public Individual getTypeOfCreatedObject() {
        return typeOfCreatedObject;
    }
}
