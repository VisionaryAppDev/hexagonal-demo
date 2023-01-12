package com.hexagon.domain.specification;


import com.hexagon.domain.entity.EdgeRouter;
import com.hexagon.domain.exception.GenericSpecificationException;
import com.hexagon.domain.specification.shared.AbstractSpecification;

public class EmptySwitchSpec extends AbstractSpecification<EdgeRouter> {

    @Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return edgeRouter.getSwitches()==null ||
                edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgeRouter edgeRouter) {
        if(!isSatisfiedBy(edgeRouter))
            throw new GenericSpecificationException("It isn't allowed to remove an edge router with a switch attached to it");
    }
}
