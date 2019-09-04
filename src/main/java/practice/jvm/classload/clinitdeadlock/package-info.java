/**
 * <pre>
 * {@code <clinit>} 可能产生dead lock
 * {@code StaticA} 的初始化static代码块中要初始化{@code StaticB}
 * {@code StaticB} 的初始化static代码块中要初始化{@code StaticA}
 * </pre>
 */
package practice.jvm.classload.clinitdeadlock;