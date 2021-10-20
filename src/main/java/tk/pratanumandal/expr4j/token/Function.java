/**
 * Copyright 2019 Pratanu Mandal
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

package tk.pratanumandal.expr4j.token;

/**
 * The <code>Function</code> interface represents functions in the expression.<br><br>
 * 
 * It is a functional interface and provides only one method evaluate() which shall be invoked to evaluate the function.<br>
 * The evaluate() method must be overridden by any implementing class.
 * 
 * @author Pratanu Mandal
 * @since 0.0.2
 *
 */
public class Function<T> extends Executable<T> {
	
	public static final int UNLIMITED_PARAMETERS = -1;
	
	public final String label;
	public final int parameters;
	
	public Function(String label, Operation<T> operation) {
		this(label, UNLIMITED_PARAMETERS, operation);
	}

	public Function(String label, int parameters, Operation<T> operation) {
		super(operation);
		this.label = label;
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return label;
	}

}
