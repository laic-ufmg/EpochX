/*
 * Copyright 2007-2011 Tom Castle & Lawrence Beadle
 * Licensed under GNU Lesser General Public License
 * 
 * This file is part of EpochX: genetic programming software for research
 * 
 * EpochX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * EpochX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with EpochX. If not, see <http://www.gnu.org/licenses/>.
 * 
 * The latest version is available from: http://www.epochx.org
 */
package org.epochx.epox.bool;

import org.epochx.epox.Node;
import org.epochx.tools.util.TypeUtils;

/**
 * A function node which performs logical conjunction or the boolean function
 * of AND.
 */
public class AndFunction extends Node {

	/**
	 * Constructs an <code>AndFunction</code> with two <code>null</code> 
	 * children.
	 */
	public AndFunction() {
		this(null, null);
	}

	/**
	 * Constructs an <code>AndFunction</code> with two <code>Boolean</code> 
	 * child nodes.
	 * 
	 * @param child1 the first child node.
	 * @param child2 the second child node.
	 */
	public AndFunction(final Node child1, final Node child2) {
		super(child1, child2);
	}

	/**
	 * Evaluates this function lazily. The first child node is evaluated, the
	 * result of which must be a <code>Boolean</code> instance. If the result
	 * is a true value then the second child is also evaluated, the result of
	 * which becomes the result of this AND function. If the first child
	 * evaluated to a false value then the second child is not evaluated at all,
	 * and a false value is returned.
	 */
	@Override
	public Boolean evaluate() {
		boolean result = ((Boolean) getChild(0).evaluate()).booleanValue();

		if (result) {
			result = ((Boolean) getChild(1).evaluate()).booleanValue();
		}

		return result;
	}

	/**
	 * Returns the identifier of this function which is AND.
	 */
	@Override
	public String getIdentifier() {
		return "AND";
	}

	/**
	 * Returns this function node's return type for the given child input types.
	 * If there are two children, both of which have a return type of 
	 * <code>Boolean</code>, then the return type of this function will also be 
	 * <code>Boolean</code>. In all other cases this method will return 
	 * <code>null</code> to indicate that the inputs are invalid.
	 * 
	 * @return The <code>Boolean</code> class or null if the input type is 
	 * invalid.
	 */
	@Override
	public Class<?> getReturnType(final Class<?> ... inputTypes) {
		if ((inputTypes.length == 2) && TypeUtils.allEqual(inputTypes, Boolean.class)) {
			return Boolean.class;
		} else {
			return null;
		}
	}
}
