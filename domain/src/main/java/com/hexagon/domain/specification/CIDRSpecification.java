package com.hexagon.domain.specification;


import com.hexagon.domain.exception.GenericSpecificationException;
import com.hexagon.domain.specification.shared.AbstractSpecification;

public class CIDRSpecification extends AbstractSpecification<Integer> {

    final static public int MINIMUM_ALLOWED_CIDR = 8;

    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr >= MINIMUM_ALLOWED_CIDR;
    }

    @Override
    public void check(Integer cidr) throws GenericSpecificationException {
        if(!isSatisfiedBy(cidr))
            throw new GenericSpecificationException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);
    }
}
