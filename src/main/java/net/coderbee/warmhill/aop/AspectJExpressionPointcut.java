package net.coderbee.warmhill.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author coderbee on 2017/12/13.
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

	private static Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

	static {
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
	}

	private PointcutExpression pointcutExpression;
	private PointcutParser pointcutParser;
	private String expression;

	public AspectJExpressionPointcut() {
		this(SUPPORTED_PRIMITIVES);
	}

	public AspectJExpressionPointcut(Set<PointcutPrimitive> primitives) {
		pointcutParser = PointcutParser
				.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(primitives);
	}

	@Override
	public boolean isMatch(Class<?> targetClass) {
		makeSureParseExpression();

		return pointcutExpression.couldMatchJoinPointsInType(targetClass);
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		makeSureParseExpression();

		ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
		if (shadowMatch.alwaysMatches()) {
			return true;
		} else if (shadowMatch.neverMatches()) {
			return false;
		}
		return false;
	}

	@Override
	public ClassFilter getClassFilter() {
		return this;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	private void makeSureParseExpression() {
		if (pointcutExpression == null) {
			pointcutExpression = pointcutParser.parsePointcutExpression(expression);
		}
	}

}
