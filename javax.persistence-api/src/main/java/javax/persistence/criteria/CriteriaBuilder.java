/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.persistence.Tuple;

/**
 * クライテリアクエリーや、複合選択、式、述語、順序付けを生成するために使用されます。
 *
 * Javaのジェネリクスは可変長引数と相性が悪いという事実を回避するためにこのAPIでは
 * <code>Expression&#060;Boolean&#062;</code>の代わりに<code>Predicate</code>が使用されることに注意してください。
 *
 * @since Java Persistence 2.0
 */
public interface CriteriaBuilder {

    /**
     *  <code>CriteriaQuery</code>のオブジェクトを作ります。
     *  @return クライテリアクエリーオブジェクト
     */
    CriteriaQuery<Object> createQuery();

    /**
     *  <code>CriteriaQuery</code>のオブジェクトを指定された結果の型で作ります。
     *  @param resultClass  クエリーの結果の型
     *  @return クライテリアクエリーオブジェクト
     */
    <T> CriteriaQuery<T> createQuery(Class<T> resultClass);

    /**
     *  <code>CriteriaQuery</code>のオブジェクトをその結果としてオブジェクト型のタプルが返ってくるものとして作ります。
     *  @return クライテリアクエリーオブジェクト
     */
    CriteriaQuery<Tuple> createTupleQuery();

    // methods to construct queries for bulk updates and deletes:

    /**
     *  バルクアップデート操作を実行するための <code>CriteriaUpdate</code> のクエリーオブジェクトを作ります。
     *  @param targetEntity  アップデート操作の対象の型
     *  @return クエリーオブジェクト
     *  @since Java Persistence 2.1
     */
    <T> CriteriaUpdate<T> createCriteriaUpdate(Class<T> targetEntity);

    /**
     *  バルクデリート操作を実行するための<code>CriteriaDelete</code> のクエリーオブジェクトを作ります。
     *  @param targetEntity  デリート操作の対象の型
     *  @return クエリーオブジェクト
     *  @since Java Persistence 2.1
     */
    <T> CriteriaDelete<T> createCriteriaDelete(Class<T> targetEntity);


    // selection construction methods:
	
    /**
     * コンストラクタに関連する選択項目を作ります。
     * 
     * このメソッドはクエリー実行の結果に適用されるコンストラクタを指定するのに使用されます。
     * コンストラクタがエンティティクラスの場合、クエリーが実行された結果のエンティティは新規状態となります。
     * @param resultClass  インスタンスが構築されるクラス
     * @param selections  コンストラクタの引数
     * @return 複合選択項目
     * @throws IllegalArgumentException 引数の一つがタプル、もしくは配列の値の選択項目の場合
     */
    <Y> CompoundSelection<Y> construct(Class<Y> resultClass, Selection<?>... selections);

    /**
     * タプルの値の選択項目を作ります。
     * @param selections  選択項目
     * @return タプルの値の複合選択
     * @throws IllegalArgumentException 引数の一つがタプル、もしくは配列の値の選択項目の場合
     */
    CompoundSelection<Tuple> tuple(Selection<?>... selections);

    /**
     * 配列の値の選択項目を作ります。
     * @param selections  選択項目
     * @return 配列の値の複合選択
     * @throws IllegalArgumentException 引数の一つがタプル、もしくは配列の値の選択項目の場合
     */
    CompoundSelection<Object[]> array(Selection<?>... selections);


    //ordering:
	
    /**
     * 式の値の昇順での順序付けを作ります。
     * @param x  順序付けの定義に使用される式
     * @return 式に関連する昇順での順序付け
     */
    Order asc(Expression<?> x);

    /**
     * 式の値の降順での順序付けを作ります。
     * @param x  順序付けの定義に使用される式
     * @return 式に関連する降順での順序付け
     */
    Order desc(Expression<?> x);

	
    //aggregate functions:
	
    /**
     * avg(平均)操作に適用される集約式を作ります。
     * @param x  avg操作への入力値を表す式
     * @return avg式
     */
    <N extends Number> Expression<Double> avg(Expression<N> x);

    /**
     * sum(合計)操作に適用される集約式を作ります。
     * @param x  sum操作への入力値を表す式
     * @return sum式
     */
    <N extends Number> Expression<N> sum(Expression<N> x);

    /**
     * Integerの値の式でLongの結果を返すsum(合計)操作に適用される集約式を作ります。
     * @param x  sum操作への入力値を表す式
     * @return sum式
     */
    Expression<Long> sumAsLong(Expression<Integer> x);

    /**
     * Floatの値の式でDoubleの結果を返すsum(合計)操作に適用される集約式を作ります。
     * @param x  sum操作への入力値を表す式
     * @return sum式
     */
    Expression<Double> sumAsDouble(Expression<Float> x);
    
    /**
     * 数値のmax(最大)操作に適用される集約式を作ります。
     * @param x  max操作への入力値を表す式
     * @return max式
     */
    <N extends Number> Expression<N> max(Expression<N> x);
    
    /**
     * 数値のmin(最小)操作に適用される集約式を作ります。
     * @param x  max操作への入力値を表す式
     * @return min式
     */
    <N extends Number> Expression<N> min(Expression<N> x);

    /**
     * (文字列や日付などの)greatest(最大値)を見つける集約式を作ります。
     * @param x  greatest操作への入力値を表す式
     * @return greatest式
     */
    <X extends Comparable<? super X>> Expression<X> greatest(Expression<X> x);
    
    /**
     * (文字列や日付などの)least(最小値)を見つける集約式を作ります。
     * @param x  least操作への入力値を表す式
     * @return least式
     */
    <X extends Comparable<? super X>> Expression<X> least(Expression<X> x);

    /**
     * count(カウント)操作に適用される集約式を作ります。
     * @param x  count操作への入力値を表す式
     * @return count式
     */
    Expression<Long> count(Expression<?> x);

    /**
     * count distinct(重複を除いたカウント)操作に適用される集約式を作ります。
     * @param x  count distinct操作への入力値を表す式
     * @return count distinct式
     */
    Expression<Long> countDistinct(Expression<?> x);
	


    //subqueries:
	
    /**
     * Create a predicate testing the existence of a subquery result.
     * @param subquery  subquery whose result is to be tested
     * @return exists predicate
     */
    Predicate exists(Subquery<?> subquery);
	
    /**
     * Create an all expression over the subquery results.
     * @param subquery  subquery
     * @return all expression
     */
    <Y> Expression<Y> all(Subquery<Y> subquery);
	
    /**
     * Create a some expression over the subquery results.
     * This expression is equivalent to an <code>any</code> expression.
     * @param subquery  subquery
     * @return some expression
     */
    <Y> Expression<Y> some(Subquery<Y> subquery);
	
    /**
     * Create an any expression over the subquery results. 
     * This expression is equivalent to a <code>some</code> expression.
     * @param subquery  subquery
     * @return any expression
     */
    <Y> Expression<Y> any(Subquery<Y> subquery);


    //boolean functions:
	
    /**
     * Create a conjunction of the given boolean expressions.
     * @param x  boolean expression
     * @param y  boolean expression
     * @return and predicate
     */
    Predicate and(Expression<Boolean> x, Expression<Boolean> y);
    
    /**
     * Create a conjunction of the given restriction predicates.
     * A conjunction of zero predicates is true.
     * @param restrictions  zero or more restriction predicates
     * @return and predicate
     */
    Predicate and(Predicate... restrictions);

    /**
     * Create a disjunction of the given boolean expressions.
     * @param x  boolean expression
     * @param y  boolean expression
     * @return or predicate
     */
    Predicate or(Expression<Boolean> x, Expression<Boolean> y);

    /**
     * Create a disjunction of the given restriction predicates.
     * A disjunction of zero predicates is false.
     * @param restrictions  zero or more restriction predicates
     * @return or predicate
     */
    Predicate or(Predicate... restrictions);

    /**
     * Create a negation of the given restriction. 
     * @param restriction  restriction expression
     * @return not predicate
     */
    Predicate not(Expression<Boolean> restriction);
	
    /**
     * Create a conjunction (with zero conjuncts).
     * A conjunction with zero conjuncts is true.
     * @return and predicate
     */
    Predicate conjunction();

    /**
     * Create a disjunction (with zero disjuncts).
     * A disjunction with zero disjuncts is false.
     * @return or predicate
     */
    Predicate disjunction();

	
    //turn Expression<Boolean> into a Predicate
    //useful for use with varargs methods

    /**
     * Create a predicate testing for a true value.
     * @param x  expression to be tested
     * @return predicate
     */
    Predicate isTrue(Expression<Boolean> x);

    /**
     * Create a predicate testing for a false value.
     * @param x  expression to be tested
     * @return predicate
     */
    Predicate isFalse(Expression<Boolean> x);

	
    //null tests:

    /**
     * Create a predicate to test whether the expression is null.
     * @param x expression
     * @return is-null predicate
     */
    Predicate isNull(Expression<?> x);

    /**
     * Create a predicate to test whether the expression is not null.
     * @param x expression
     * @return is-not-null predicate
     */
    Predicate isNotNull(Expression<?> x);

    //equality:
	
    /**
     * Create a predicate for testing the arguments for equality.
     * @param x  expression
     * @param y  expression
     * @return equality predicate
     */
    Predicate equal(Expression<?> x, Expression<?> y);
	
    /**
     * Create a predicate for testing the arguments for equality.
     * @param x  expression
     * @param y  object
     * @return equality predicate
     */
    Predicate equal(Expression<?> x, Object y);

    /**
     * Create a predicate for testing the arguments for inequality.
     * @param x  expression
     * @param y  expression
     * @return inequality predicate
     */
    Predicate notEqual(Expression<?> x, Expression<?> y);
	
    /**
     * Create a predicate for testing the arguments for inequality.
     * @param x  expression
     * @param y  object
     * @return inequality predicate
     */
    Predicate notEqual(Expression<?> x, Object y);

	
    //comparisons for generic (non-numeric) operands:

    /**
     * Create a predicate for testing whether the first argument is 
     * greater than the second.
     * @param x  expression
     * @param y  expression
     * @return greater-than predicate
     */
    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Expression<? extends Y> y);
	
    /**
     * Create a predicate for testing whether the first argument is 
     * greater than the second.
     * @param x  expression
     * @param y  value
     * @return greater-than predicate
     */
    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Y y);
    
    /**
     * Create a predicate for testing whether the first argument is 
     * greater than or equal to the second.
     * @param x  expression
     * @param y  expression
     * @return greater-than-or-equal predicate
     */
    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * greater than or equal to the second.
     * @param x  expression
     * @param y  value
     * @return greater-than-or-equal predicate
     */
    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than the second.
     * @param x  expression
     * @param y  expression
     * @return less-than predicate
     */
    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than the second.
     * @param x  expression
     * @param y  value
     * @return less-than predicate
     */
    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y);
	
    /**
     * Create a predicate for testing whether the first argument is 
     * less than or equal to the second.
     * @param x  expression
     * @param y  expression
     * @return less-than-or-equal predicate
     */
    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than or equal to the second.
     * @param x  expression
     * @param y  value
     * @return less-than-or-equal predicate
     */
    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y);

    /**
     * Create a predicate for testing whether the first argument is 
     * between the second and third arguments in value.
     * @param v  expression 
     * @param x  expression
     * @param y  expression
     * @return between predicate
     */
    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * between the second and third arguments in value.
     * @param v  expression 
     * @param x  value
     * @param y  value
     * @return between predicate
     */
    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Y x, Y y);
	

    //comparisons for numeric operands:
	
    /**
     * Create a predicate for testing whether the first argument is 
     * greater than the second.
     * @param x  expression
     * @param y  expression
     * @return greater-than predicate
     */
    Predicate gt(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * greater than the second.
     * @param x  expression
     * @param y  value
     * @return greater-than predicate
     */
    Predicate gt(Expression<? extends Number> x, Number y);

    /**
     * Create a predicate for testing whether the first argument is 
     * greater than or equal to the second.
     * @param x  expression
     * @param y  expression
     * @return greater-than-or-equal predicate
     */
    Predicate ge(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * greater than or equal to the second.
     * @param x  expression
     * @param y  value
     * @return greater-than-or-equal predicate
     */	
    Predicate ge(Expression<? extends Number> x, Number y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than the second.
     * @param x  expression
     * @param y  expression
     * @return less-than predicate
     */
    Predicate lt(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than the second.
     * @param x  expression
     * @param y  value
     * @return less-than predicate
     */
    Predicate lt(Expression<? extends Number> x, Number y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than or equal to the second.
     * @param x  expression
     * @param y  expression
     * @return less-than-or-equal predicate
     */
    Predicate le(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * Create a predicate for testing whether the first argument is 
     * less than or equal to the second.
     * @param x  expression
     * @param y  value
     * @return less-than-or-equal predicate
     */
    Predicate le(Expression<? extends Number> x, Number y);
	

    //numerical operations:
	
    /**
     * Create an expression that returns the arithmetic negation
     * of its argument.
     * @param x expression
     * @return arithmetic negation
     */
    <N extends Number> Expression<N> neg(Expression<N> x);

    /**
     * Create an expression that returns the absolute value
     * of its argument.
     * @param x expression
     * @return absolute value
     */
    <N extends Number> Expression<N> abs(Expression<N> x);
	
    /**
     * Create an expression that returns the sum
     * of its arguments.
     * @param x expression
     * @param y expression
     * @return sum
     */
    <N extends Number> Expression<N> sum(Expression<? extends N> x, Expression<? extends N> y);
	
    /**
     * Create an expression that returns the sum
     * of its arguments.
     * @param x expression
     * @param y value
     * @return sum
     */
    <N extends Number> Expression<N> sum(Expression<? extends N> x, N y);

    /**
     * Create an expression that returns the sum
     * of its arguments.
     * @param x value
     * @param y expression
     * @return sum
     */
    <N extends Number> Expression<N> sum(N x, Expression<? extends N> y);

    /**
     * Create an expression that returns the product
     * of its arguments.
     * @param x expression
     * @param y expression
     * @return product
     */
    <N extends Number> Expression<N> prod(Expression<? extends N> x, Expression<? extends N> y);

    /**
     * Create an expression that returns the product
     * of its arguments.
     * @param x expression
     * @param y value
     * @return product
     */
    <N extends Number> Expression<N> prod(Expression<? extends N> x, N y);

    /**
     * Create an expression that returns the product
     * of its arguments.
     * @param x value
     * @param y expression
     * @return product
     */
    <N extends Number> Expression<N> prod(N x, Expression<? extends N> y);

    /**
     * Create an expression that returns the difference
     * between its arguments.
     * @param x expression
     * @param y expression
     * @return difference
     */
    <N extends Number> Expression<N> diff(Expression<? extends N> x, Expression<? extends N> y);

    /**
     * Create an expression that returns the difference
     * between its arguments.
     * @param x expression
     * @param y value
     * @return difference
     */
    <N extends Number> Expression<N> diff(Expression<? extends N> x, N y);

    /**
     * Create an expression that returns the difference
     * between its arguments.
     * @param x value
     * @param y expression
     * @return difference
     */
    <N extends Number> Expression<N> diff(N x, Expression<? extends N> y);
	
    /**
     * Create an expression that returns the quotient
     * of its arguments.
     * @param x expression
     * @param y expression
     * @return quotient
     */
    Expression<Number> quot(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * Create an expression that returns the quotient
     * of its arguments.
     * @param x expression
     * @param y value
     * @return quotient
     */
    Expression<Number> quot(Expression<? extends Number> x, Number y);

    /**
     * Create an expression that returns the quotient
     * of its arguments.
     * @param x value
     * @param y expression
     * @return quotient
     */
    Expression<Number> quot(Number x, Expression<? extends Number> y);
	
    /**
     * Create an expression that returns the modulus
     * of its arguments.
     * @param x expression
     * @param y expression
     * @return modulus
     */
    Expression<Integer> mod(Expression<Integer> x, Expression<Integer> y);
	
    /**
     * Create an expression that returns the modulus
     * of its arguments.
     * @param x expression
     * @param y value
     * @return modulus
     */
    Expression<Integer> mod(Expression<Integer> x, Integer y);

    /**
     * Create an expression that returns the modulus
     * of its arguments.
     * @param x value
     * @param y expression
     * @return modulus
     */
    Expression<Integer> mod(Integer x, Expression<Integer> y);

    /**
     * Create an expression that returns the square root
     * of its argument.
     * @param x expression
     * @return square root
     */	
    Expression<Double> sqrt(Expression<? extends Number> x);

	
    //typecasts:
    
    /**
     * Typecast.  Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;Long&#062;
     */
    Expression<Long> toLong(Expression<? extends Number> number);

    /**
     * Typecast.  Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;Integer&#062;
     */
    Expression<Integer> toInteger(Expression<? extends Number> number);

    /**
     * Typecast. Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;Float&#062;
     */
    Expression<Float> toFloat(Expression<? extends Number> number);

    /**
     * Typecast.  Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;Double&#062;
     */
    Expression<Double> toDouble(Expression<? extends Number> number);

    /**
     * Typecast.  Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;BigDecimal&#062;
     */
    Expression<BigDecimal> toBigDecimal(Expression<? extends Number> number);

    /**
     * Typecast.  Returns same expression object.
     * @param number  numeric expression
     * @return Expression&#060;BigInteger&#062;
     */
    Expression<BigInteger> toBigInteger(Expression<? extends Number> number);
	
    /**
     * Typecast.  Returns same expression object.
     * @param character expression
     * @return Expression&#060;String&#062;
     */
    Expression<String> toString(Expression<Character> character);

	
    //literals:

    /**
     * Create an expression for a literal.
     * @param value  value represented by the expression
     * @return expression literal
     * @throws IllegalArgumentException if value is null
     */
    <T> Expression<T> literal(T value);

    /**
     * Create an expression for a null literal with the given type.
     * @param resultClass  type of the null literal
     * @return null expression literal
     */
    <T> Expression<T> nullLiteral(Class<T> resultClass);

    //parameters:

    /**
     * Create a parameter expression.
     * @param paramClass parameter class
     * @return parameter expression
     */
    <T> ParameterExpression<T> parameter(Class<T> paramClass);

    /**
     * Create a parameter expression with the given name.
     * @param paramClass parameter class
     * @param name  name that can be used to refer to 
     *              the parameter
     * @return parameter expression
     */
    <T> ParameterExpression<T> parameter(Class<T> paramClass, String name);


    //collection operations:
	
    /**
     *  Create a predicate that tests whether a collection is empty.
     *  @param collection expression
     *  @return is-empty predicate
     */
    <C extends Collection<?>> Predicate isEmpty(Expression<C> collection);

    /**
     *  Create a predicate that tests whether a collection is
     *  not empty.
     *  @param collection expression
     *  @return is-not-empty predicate
     */
    <C extends Collection<?>> Predicate isNotEmpty(Expression<C> collection);

    /**
     * Create an expression that tests the size of a collection.
     * @param collection expression
     * @return size expression
     */ 
    <C extends java.util.Collection<?>> Expression<Integer> size(Expression<C> collection);
	
    /**
     * Create an expression that tests the size of a collection.
     * @param collection collection
     * @return size expression
     */ 
    <C extends Collection<?>> Expression<Integer> size(C collection);
	
    /**
     *  Create a predicate that tests whether an element is
     *  a member of a collection.
     *  If the collection is empty, the predicate will be false.
     *  @param elem element expression
     *  @param collection expression
     *  @return is-member predicate
     */
    <E, C extends Collection<E>> Predicate isMember(Expression<E> elem, Expression<C> collection);

    /**
     *  Create a predicate that tests whether an element is
     *  a member of a collection.
     *  If the collection is empty, the predicate will be false.
     *  @param elem element
     *  @param collection expression
     *  @return is-member predicate
     */
    <E, C extends Collection<E>> Predicate isMember(E elem, Expression<C> collection);

    /**
     *  Create a predicate that tests whether an element is
     *  not a member of a collection.
     *  If the collection is empty, the predicate will be true.
     *  @param elem element expression
     *  @param collection expression
     *  @return is-not-member predicate
     */
    <E, C extends Collection<E>> Predicate isNotMember(Expression<E> elem, Expression<C> collection);
	
    /**
     *  Create a predicate that tests whether an element is
     *  not a member of a collection.
     *  If the collection is empty, the predicate will be true.
     *  @param elem element
     *  @param collection expression
     *  @return is-not-member predicate
     */
    <E, C extends Collection<E>> Predicate isNotMember(E elem, Expression<C> collection);


    //get the values and keys collections of the Map, which may then
    //be passed to size(), isMember(), isEmpty(), etc

    /**
     * Create an expression that returns the values of a map.
     * @param map  map
     * @return collection expression
     */
    <V, M extends Map<?, V>> Expression<Collection<V>> values(M map);

    /**
     * Create an expression that returns the keys of a map.
     * @param map  map
     * @return set expression
     */
    <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map);

	
    //string functions:
	
    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @return like predicate
     */
    Predicate like(Expression<String> x, Expression<String> pattern);
	
    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @return like predicate
     */
    Predicate like(Expression<String> x, String pattern);
	
    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @param escapeChar  escape character expression
     * @return like predicate
     */
    Predicate like(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);
	
    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @param escapeChar  escape character
     * @return like predicate
     */
    Predicate like(Expression<String> x, Expression<String> pattern, char escapeChar);
	
    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @param escapeChar  escape character expression
     * @return like predicate
     */
    Predicate like(Expression<String> x, String pattern, Expression<Character> escapeChar);

    /**
     * Create a predicate for testing whether the expression
     * satisfies the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @param escapeChar  escape character
     * @return like predicate
     */
    Predicate like(Expression<String> x, String pattern, char escapeChar);
	
    /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern);
	
    /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, String pattern);

    /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @param escapeChar  escape character expression
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);

    /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string expression
     * @param escapeChar  escape character
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern, char escapeChar);

    /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @param escapeChar  escape character expression
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, String pattern, Expression<Character> escapeChar);
	
   /**
     * Create a predicate for testing whether the expression
     * does not satisfy the given pattern.
     * @param x  string expression
     * @param pattern  string 
     * @param escapeChar  escape character
     * @return not-like predicate
     */
    Predicate notLike(Expression<String> x, String pattern, char escapeChar);

    /**
     *  Create an expression for string concatenation.
     *  @param x  string expression
     *  @param y  string expression
     *  @return expression corresponding to concatenation
     */
    Expression<String> concat(Expression<String> x, Expression<String> y);
	
    /**
     *  Create an expression for string concatenation.
     *  @param x  string expression
     *  @param y  string 
     *  @return expression corresponding to concatenation
     */
    Expression<String> concat(Expression<String> x, String y);

    /**
     *  Create an expression for string concatenation.
     *  @param x  string 
     *  @param y  string expression
     *  @return expression corresponding to concatenation
     */
    Expression<String> concat(String x, Expression<String> y);
	
    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring starting at the specified position
     *  through to end of the string.
     *  First position is 1.
     *  @param x  string expression
     *  @param from  start position expression 
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, Expression<Integer> from);
	
    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring starting at the specified position
     *  through to end of the string.
     *  First position is 1.
     *  @param x  string expression
     *  @param from  start position 
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, int from);

    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring of given length starting at the
     *  specified position.
     *  First position is 1.
     *  @param x  string expression
     *  @param from  start position expression 
     *  @param len  length expression
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, Expression<Integer> from, Expression<Integer> len);
	
    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring of given length starting at the
     *  specified position.
     *  First position is 1.
     *  @param x  string expression
     *  @param from  start position 
     *  @param len  length
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, int from, int len);
	
    /**
     *  Used to specify how strings are trimmed.
     */
    public static enum Trimspec { 

        /**
         * Trim from leading end.
         */
        LEADING,
 
        /**
         * Trim from trailing end.
         */
        TRAILING, 

        /**
         * Trim from both ends.
         */
        BOTH 
    }
	
    /**
     * Create expression to trim blanks from both ends of
     * a string.
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(Expression<String> x);
	
    /**
     * Create expression to trim blanks from a string.
     * @param ts  trim specification
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(Trimspec ts, Expression<String> x);

    /**
     * Create expression to trim character from both ends of
     * a string.
     * @param t  expression for character to be trimmed
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(Expression<Character> t, Expression<String> x);

    /**
     * Create expression to trim character from a string.
     * @param ts  trim specification
     * @param t  expression for character to be trimmed
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(Trimspec ts, Expression<Character> t, Expression<String> x);
	
    /**
     * Create expression to trim character from both ends of
     * a string.
     * @param t  character to be trimmed
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(char t, Expression<String> x);
	
    /**
     * Create expression to trim character from a string.
     * @param ts  trim specification
     * @param t  character to be trimmed
     * @param x  expression for string to trim
     * @return trim expression
     */
    Expression<String> trim(Trimspec ts, char t, Expression<String> x);
	
    /**
     * Create expression for converting a string to lowercase.
     * @param x  string expression
     * @return expression to convert to lowercase
     */
    Expression<String> lower(Expression<String> x);
	
    /**
     * Create expression for converting a string to uppercase.
     * @param x  string expression
     * @return expression to convert to uppercase
     */
    Expression<String> upper(Expression<String> x);
	
    /**
     * Create expression to return length of a string.
     * @param x  string expression
     * @return length expression
     */
    Expression<Integer> length(Expression<String> x);
	
	
    /**
     * Create expression to locate the position of one string
     * within another, returning position of first character
     * if found.
     * The first position in a string is denoted by 1.  If the
     * string to be located is not found, 0 is returned.
     * @param x  expression for string to be searched
     * @param pattern  expression for string to be located
     * @return expression corresponding to position
     */
    Expression<Integer> locate(Expression<String> x, Expression<String> pattern);
	
    /**
     * Create expression to locate the position of one string
     * within another, returning position of first character
     * if found.
     * The first position in a string is denoted by 1.  If the
     * string to be located is not found, 0 is returned.
     * @param x  expression for string to be searched
     * @param pattern  string to be located
     * @return expression corresponding to position
     */
    Expression<Integer> locate(Expression<String> x, String pattern);

    /**
     * Create expression to locate the position of one string
     * within another, returning position of first character
     * if found.
     * The first position in a string is denoted by 1.  If the
     * string to be located is not found, 0 is returned.
     * @param x  expression for string to be searched
     * @param pattern  expression for string to be located
     * @param from  expression for position at which to start search
     * @return expression corresponding to position
     */
    Expression<Integer> locate(Expression<String> x, Expression<String> pattern, Expression<Integer> from);

    /**
     * Create expression to locate the position of one string
     * within another, returning position of first character
     * if found.
     * The first position in a string is denoted by 1.  If the
     * string to be located is not found, 0 is returned.
     * @param x  expression for string to be searched
     * @param pattern  string to be located
     * @param from  position at which to start search
     * @return expression corresponding to position
     */	
    Expression<Integer> locate(Expression<String> x, String pattern, int from);
	

    // Date/time/timestamp functions:

    /**
     *  Create expression to return current date.
     *  @return expression for current date
     */
    Expression<java.sql.Date> currentDate();

    /**
     *  Create expression to return current timestamp.
     *  @return expression for current timestamp
     */	
    Expression<java.sql.Timestamp> currentTimestamp();

    /**
     *  Create expression to return current time.
     *  @return expression for current time
     */	
    Expression<java.sql.Time> currentTime();
	

    //in builders:
	
    /**
     *  Interface used to build in predicates.
     */
    public static interface In<T> extends Predicate {

         /**
          * Return the expression to be tested against the
          * list of values.
          * @return expression
          */
         Expression<T> getExpression();
	
         /**
          *  Add to list of values to be tested against.
          *  @param value value
          *  @return in predicate
          */
         In<T> value(T value);

         /**
          *  Add to list of values to be tested against.
          *  @param value expression
          *  @return in predicate
          */
         In<T> value(Expression<? extends T> value);
     }
	
    /**
     *  Create predicate to test whether given expression
     *  is contained in a list of values.
     *  @param  expression to be tested against list of values
     *  @return  in predicate
     */
    <T> In<T> in(Expression<? extends T> expression);
	

    // coalesce, nullif:

    /**
     * Create an expression that returns null if all its arguments
     * evaluate to null, and the value of the first non-null argument
     * otherwise.
     * @param x expression
     * @param y expression
     * @return coalesce expression
     */
    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create an expression that returns null if all its arguments
     * evaluate to null, and the value of the first non-null argument
     * otherwise.
     * @param x expression
     * @param y value
     * @return coalesce expression
     */
    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Y y);
    
    /**
     * Create an expression that tests whether its argument are
     * equal, returning null if they are and the value of the
     * first expression if they are not.
     * @param x expression
     * @param y expression
     * @return nullif expression
     */
    <Y> Expression<Y> nullif(Expression<Y> x, Expression<?> y);

    /**
     * Create an expression that tests whether its argument are
     * equal, returning null if they are and the value of the
     * first expression if they are not.
     * @param x expression
     * @param y value
     * @return nullif expression 
     */
    <Y> Expression<Y> nullif(Expression<Y> x, Y y);


    // coalesce builder:

    /**
     *  Interface used to build coalesce expressions.  
     *   
     * A coalesce expression is equivalent to a case expression
     * that returns null if all its arguments evaluate to null,
     * and the value of its first non-null argument otherwise.
     */
    public static interface Coalesce<T> extends Expression<T> {

         /**
          * Add an argument to the coalesce expression.
          * @param value  value
          * @return coalesce expression
          */
         Coalesce<T> value(T value);

         /**
          * Add an argument to the coalesce expression.
          * @param value expression
          * @return coalesce expression
          */
         Coalesce<T> value(Expression<? extends T> value);
	}
	
    /**
     * Create a coalesce expression.
     * @return coalesce expression
     */
    <T> Coalesce<T> coalesce();


    //case builders:

    /**
     *  簡単なcase式を構築するために使用するインターフェースです。
     *  case式の条件は指定された順番で評価されます。
     */
    public static interface SimpleCase<C,R> extends Expression<R> {

		/**
		 * Return the expression to be tested against the
		 * conditions.
		 * @return expression
		 */
		Expression<C> getExpression();

		/**
		 * Add a when/then clause to the case expression.
		 * @param condition  "when" condition
		 * @param result  "then" result value
		 * @return simple case expression
		 */
		SimpleCase<C, R> when(C condition, R result);

		/**
		 * Add a when/then clause to the case expression.
		 * @param condition  "when" condition
		 * @param result  "then" result expression
		 * @return simple case expression
		 */
		SimpleCase<C, R> when(C condition, Expression<? extends R> result);

		/**
		 * Add an "else" clause to the case expression.
		 * @param result  "else" result
		 * @return expression
		 */
		Expression<R> otherwise(R result);

		/**
		 * Add an "else" clause to the case expression.
		 * @param result  "else" result expression
		 * @return expression
		 */
		Expression<R> otherwise(Expression<? extends R> result);
	}
	
    /**
     *  簡単なcase式を作成します。
     *  @param expression  to be tested against the case conditions
     *  @return simple case expression
     */
    <C, R> SimpleCase<C,R> selectCase(Expression<? extends C> expression);


    /**
     *  一般的なcase式を構築するために使用するインターフェースです。
     *  case式の条件は指定された順番で評価されます。
     */
    public static interface Case<R> extends Expression<R> {

		/**
		 * Add a when/then clause to the case expression.
		 * @param condition  "when" condition
		 * @param result  "then" result value
		 * @return general case expression
		 */
		Case<R> when(Expression<Boolean> condition, R result);

		/**
		 * Add a when/then clause to the case expression.
		 * @param condition  "when" condition
		 * @param result  "then" result expression
		 * @return general case expression
		 */
		Case<R> when(Expression<Boolean> condition, Expression<? extends R> result);

		/**
		 * Add an "else" clause to the case expression.
		 * @param result  "else" result
		 * @return expression
		 */
		Expression<R> otherwise(R result);

		/**
		 * Add an "else" clause to the case expression.
		 * @param result  "else" result expression
		 * @return expression
		 */
		Expression<R> otherwise(Expression<? extends R> result);
	}
	
    /**
     *  一般的なcase式を作成します。
     *  @return 一般的なcase式
     */
    <R> Case<R> selectCase();

    /**
     * データベースの関数を実行するための指揮を作成します。
     * @param name  関数の名前
     * @param type  予期された結果の型
     * @param args  関数の引数
     * @return 式
     */
   <T> Expression<T> function(String name, Class<T> type,
Expression<?>... args);


    // methods for downcasting:

    /**
     *  指定された型にJoinオブジェクトをダウンキャストします。
     *  @param join  Joinオブジェクト
     *  @param type ダウンキャストされる型
     *  @return  指定された型のJoinオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T, V extends T> Join<X, V> treat(Join<X, T> join, Class<V> type);

    /**
     *  指定された型にCollectionJoinオブジェクトをダウンキャストします。
     *  @param join  CollectionJoinオブジェクト
     *  @param type ダウンキャストされる型
     *  @return  指定された型のCollectionJoinオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T, E extends T> CollectionJoin<X, E> treat(CollectionJoin<X, T> join, Class<E> type);

    /**
     *  指定された型にSetJoinオブジェクトをダウンキャストします。
     *  @param join  SetJoinオブジェクト
     *  @param type ダウンキャストされる型
     *  @return  指定された型のSetJoinオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T, E extends T> SetJoin<X, E> treat(SetJoin<X, T> join, Class<E> type);

    /**
     *  指定された型にListJoinオブジェクトをダウンキャストします。
     *  @param join  ListJoinオブジェクト
     *  @param type ダウンキャストされる型
     *  @return  指定された型のListJoinオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T, E extends T> ListJoin<X, E> treat(ListJoin<X, T> join, Class<E> type);

    /**
     *  指定された型にMapJoinオブジェクトをダウンキャストします。
     *  @param join  MapJoinオブジェクト
     *  @param type ダウンキャストされる型
     *  @return  指定された型のMapJoinオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, K, T, V extends T> MapJoin<X, K, V> treat(MapJoin<X, K, T> join, Class<V> type);


    /**
     *  指定された型にPathオブジェクトをダウンキャストします。
     *  @param path  path
     *  @param type ダウンキャストされる型
     *  @return  指定された型のPathオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T extends X> Path<T> treat(Path<X> path, Class<T> type);

    /**
     *  指定された型にRootオブジェクトをダウンキャストします。
     *  @param root  root
     *  @param type ダウンキャストされる型
     *  @return  指定された型のRootオブジェクト
     *  @since Java Persistence 2.1
     */
    <X, T extends X> Root<T> treat(Root<X> root, Class<T> type);

}




