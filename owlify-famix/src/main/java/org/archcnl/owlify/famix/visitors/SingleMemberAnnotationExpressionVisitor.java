package org.archcnl.owlify.famix.visitors;

import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.jena.ontology.Individual;
import org.archcnl.owlify.famix.ontology.FamixOntology;

public class SingleMemberAnnotationExpressionVisitor extends VoidVisitorAdapter<Void> {

    private FamixOntology ontology;
    private Individual annotatedEntity;

    public SingleMemberAnnotationExpressionVisitor(
            FamixOntology famixOntology, Individual annotatedEntity) {
        this.ontology = famixOntology;
        this.annotatedEntity = annotatedEntity;
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Void arg) {
        n.getMemberValue();

        Individual annotationIndividual =
                ontology.getAnnotationTypeIndividualWithName(n.getNameAsString());
        ontology.setHasAnnotationInstanceForEntity(annotationIndividual, annotatedEntity);

        // TODO Annotation Attributes
    }
}
