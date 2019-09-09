/**
 * Copyright 2019 Pratanu Mandal
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 * 
 * 		The above copyright notice and this permission notice shall be included
 * 		in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

package tk.pratanumandal.expr4j.token;

/**
 * The <code>Token</code> class represents any token in expressions.<br><br>
 * A token is the smallest indivisible unit of any expression.<br>
 * Tokens can be operands, operators, functions, variables, or constants.
 * 
 * @author Pratanu Mandal
 *
 */
public abstract class Token {

	/**
	 * The string value of the token.
	 */
	public String value;

	/**
	 * Parameterized constructor.
	 * 
	 * @param value The value of the token as a String
	 */
	public Token(String value) {
		super();
		this.value = value;
	}
	
	/**
	 * Method to convert token object to string.
	 * 
	 * @return The string value of this token
	 */
	@Override
	public String toString() {
		return value;
	}
	
}