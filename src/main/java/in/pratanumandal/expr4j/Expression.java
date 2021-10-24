/**
 * Copyright 2021 Pratanu Mandal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package in.pratanumandal.expr4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.pratanumandal.expr4j.exception.Expr4jException;
import in.pratanumandal.expr4j.token.Function;
import in.pratanumandal.expr4j.token.Operand;
import in.pratanumandal.expr4j.token.Operator;
import in.pratanumandal.expr4j.token.Operator.OperatorType;
import in.pratanumandal.expr4j.token.Token;
import in.pratanumandal.expr4j.token.Variable;

/**
 * The <code>Expression&lt;T&gt;</code> class represents a parsed expression that can be evaluated.
 * 
 * @author Pratanu Mandal
 * @since 1.0
 *
 * @param <T> The type of operand for this expression
 */
public class Expression<T> {
	
	/**
	 * The <code>Node</code> class represents a node of the expression tree.<br><br>
	 * 
	 * @author Pratanu Mandal
	 *
	 */
	public static class Node {
		/**
		 * Children of this node.
		 */
		public final List<Node> children;
		
		/**
		 * Token contained in this node.<br>
		 * A token can be an operand, operator, function, variable, or constant.
		 */
		public final Token token;

		/**
		 * Parameterized constructor.
		 * 
		 * @param token The token in this node
		 */
		public Node(Token token) {
			this.token = token;
			if (token instanceof Function || token instanceof Operator) {
				this.children = new ArrayList<Expression.Node>();
			}
			else {
				this.children = null;
			}
		}
	}
	
	/**
	 * Root node of the expression tree.
	 */
	public Node root;
	
	/**
	 * Map to hold the constants.
	 */
	private final Map<String, T> constants;
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param constants Map of constants
	 */
	public Expression(Map<String, T> constants) {
		this.constants = new HashMap<>(constants);
	}

	/**
	 * Get unmodifiable map of constants for this expression.
	 * 
	 * @return Map of constants
	 */
	public Map<String, T> getConstants() {
		return Collections.unmodifiableMap(constants);
	}
	
	/**
	 * Recursively evaluate the expression tree and return the result.
	 * 
	 * @param node Current node of the expression tree
	 * @param variables Map of variables
	 * @return Result of expression evaluation
	 */
	@SuppressWarnings("unchecked")
	protected Operand<T> evaluate(Node node, Map<String, T> variables) {
		// encountered variable
		if (node.token instanceof Variable) {
			Variable variable = (Variable) node.token;
			
			if (!variables.containsKey(variable.label)) {
				throw new Expr4jException("Variable not found: " + variable.label);
			}
			
			return new Operand<T>(variables.get(variable.label));
		}
		
		// encountered function
		else if (node.token instanceof Function) {
			Function<T> function = (Function<T>) node.token;
			
			int operandCount = function.parameters;
			if (node.children.size() != operandCount) {
				throw new Expr4jException("Invalid expression");
			}
			
			List<T> operands = new ArrayList<>();
			for (int i = 0; i < operandCount; i++) {
				operands.add(evaluate(node.children.get(i), variables).value);
			}
			
			return new Operand<T>(function.evaluate(operands));
		}
		
		// encountered operator
		else if (node.token instanceof Operator) {
			Operator<T> operator = (Operator<T>) node.token;
			
			int operandCount = (operator.operatorType == OperatorType.INFIX || operator.operatorType == OperatorType.INFIX_RTL) ? 2 : 1;
			if (node.children.size() != operandCount) {
				throw new Expr4jException("Invalid expression");
			}
			
			List<T> operands = new ArrayList<>();
			for (int i = 0; i < operandCount; i++) {
				operands.add(evaluate(node.children.get(i), variables).value);
			}
			
			return new Operand<T>(operator.evaluate(operands));
		}
		
		// encountered operand
		else {
			return (Operand<T>) node.token;
		}
	}
	
	/**
	 * Evaluate the expression against a set of variables.<br>
	 * Variables passed to this method override an predefined constants with the same label.
	 * 
	 * @param variables Map of variables
	 * @return Evaluated result
	 */
	public T evaluate(Map<String, T> variables) {
		if (root == null) {
			throw new Expr4jException("Invalid expression");
		}
		
		Map<String, T> constantsAndVariables = new HashMap<>(constants);
		if (variables != null) constantsAndVariables.putAll(variables);
		
		return evaluate(root, constantsAndVariables).value;
	}
	
	/**
	 * Evaluate the expression.
	 * 
	 * @return Evaluated result
	 */
	public T evaluate() {
		return evaluate(new HashMap<String, T>());
	}
	
}