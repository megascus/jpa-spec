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
     * サブクエリーの結果の存在を検証する述語を作ります。
     * @param subquery  結果を検証されるサブクエリー
     * @return exists 述語
     */
    Predicate exists(Subquery<?> subquery);
	
    /**
     * サブクエリの結果に対するall式を作ります。
     * @param subquery  サブクエリー
     * @return all 式
     */
    <Y> Expression<Y> all(Subquery<Y> subquery);
	
    /**
     * サブクエリの結果に対するsome式を作ります。
     * 
     * この式は<code>any</code>式と等価です。
     * @param subquery  サブクエリー
     * @return some 式
     */
    <Y> Expression<Y> some(Subquery<Y> subquery);
	
    /**
     * サブクエリの結果に対するany式を作ります。
     * 
     * この式は<code>some</code>式と等価です。
     * @param subquery  サブクエリー
     * @return any 式
     */
    <Y> Expression<Y> any(Subquery<Y> subquery);


    //boolean functions:
	
    /**
     * 与えられたブール式の論理積を作ります。
     * @param x  ブール式
     * @param y  ブール式
     * @return and 述語
     */
    Predicate and(Expression<Boolean> x, Expression<Boolean> y);
    
    /**
     * 与えられた限定述語の論理積を作ります。
     * 
     * 0個の述語の論理積はtrueです。
     * @param restrictions  0個以上の限定述語
     * @return and 述語
     */
    Predicate and(Predicate... restrictions);

    /**
     * 与えられたブール式の論理和を作ります。
     * @param x  ブール式
     * @param y  ブール式
     * @return or 述語
     */
    Predicate or(Expression<Boolean> x, Expression<Boolean> y);

    /**
     * 与えられた限定述語の論理和を作ります。
     * 
     * 0個の述語の論理和はtrueです。
     * @param restrictions  0個以上の限定述語
     * @return or 述語
     */
    Predicate or(Predicate... restrictions);

    /**
     * 与えられた限定の否定を作ります。
     * @param restriction  限定式
     * @return not 述語
     */
    Predicate not(Expression<Boolean> restriction);
	
    /**
     * (0個の結合詞で)論理積を作ります。
     * 
     * 0個の結合詞の論理積はtrueです。
     * 
     * @return and 述語
     */
    Predicate conjunction();

    /**
     * (0個の離接で)論理和を作ります。
     * 
     * 0個の離接の論理和はfalseです。
     * @return or 述語
     */
    Predicate disjunction();

	
    //turn Expression<Boolean> into a Predicate
    //useful for use with varargs methods

    /**
     * trueの値を検査する述語を作ります。
     * @param x  検査される式
     * @return 述語
     */
    Predicate isTrue(Expression<Boolean> x);

    /**
     * falseの値を検査する述語を作ります。
     * @param x  検査される式
     * @return 述語
     */
    Predicate isFalse(Expression<Boolean> x);

	
    //null tests:

    /**
     * 式がnullかどうかを検査する述語を作ります。
     * @param x 式
     * @return is-null 述語
     */
    Predicate isNull(Expression<?> x);

    /**
     * 式がnullでないかどうかを検査する述語を作ります。
     * @param x 式
     * @return is-not-null 述語
     */
    Predicate isNotNull(Expression<?> x);

    //equality:
	
    /**
     * 引数が等しいことを検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return 等価の述語
     */
    Predicate equal(Expression<?> x, Expression<?> y);
	
    /**
     * 引数が等しいことを検証するための述語を作ります。
     * @param x  式
     * @param y  オブジェクト
     * @return 等価の述語
     */
    Predicate equal(Expression<?> x, Object y);

    /**
     * 引数が等しくないことを検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return 不等価の述語
     */
    Predicate notEqual(Expression<?> x, Expression<?> y);
	
    /**
     * 引数が等しくないことを検証するための述語を作ります。
     * @param x  式
     * @param y  オブジェクト
     * @return 不等価の述語
     */
    Predicate notEqual(Expression<?> x, Object y);

	
    //comparisons for generic (non-numeric) operands:

    /**
     * 最初の引数が二つ目の引数より大きいかどうかを検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return greater-than 述語
     */
    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Expression<? extends Y> y);
	
    /**
     * 最初の引数が二つ目の引数より大きいかどうかを検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return greater-than 述語
     */
    <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Y y);
    
    /**
     * 最初の引数が二つ目の引数以上であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return greater-than-or-equal 述語
     */
    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * 最初の引数が二つ目の引数以上であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return greater-than-or-equal 述語
     */
    <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y);

    /**
     * 最初の引数が二つ目の引数未満であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return less-than 述語
     */
    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * 最初の引数が二つ目の引数未満であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return less-than 述語
     */
    <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y);
	
    /**
     * 最初の引数が二つ目の引数以下であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return less-than-or-equal 述語
     */
    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * 最初の引数が二つ目の引数以下であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return less-than-or-equal 述語
     */
    <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y);

    /**
     * 最初の引数が二つ目と三つ目の引数の間の値であるか検証するための述語を作ります。
     * @param v  式 
     * @param x  式
     * @param y  式
     * @return between 述語
     */
    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * 最初の引数が二つ目と三つ目の引数の間の値であるか検証するための述語を作ります。
     * @param v  式 
     * @param x  値
     * @param y  値
     * @return between 述語
     */
    <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Y x, Y y);
	

    //comparisons for numeric operands:
	
    /**
     * 最初の引数が二つ目の引数より大きいかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return greater-than 述語
     */
    Predicate gt(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * 最初の引数が二つ目の引数より大きいかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return greater-than 述語
     */
    Predicate gt(Expression<? extends Number> x, Number y);

    /**
     * 最初の引数が二つ目の引数以上であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return greater-than-or-equal 述語
     */
    Predicate ge(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * 最初の引数が二つ目の引数以上であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return greater-than-or-equal 述語
     */	
    Predicate ge(Expression<? extends Number> x, Number y);

    /**
     * 最初の引数が二つ目の引数未満であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return less-than 述語
     */
    Predicate lt(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * 最初の引数が二つ目の引数未満であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return less-than 述語
     */
    Predicate lt(Expression<? extends Number> x, Number y);

    /**
     * 最初の引数が二つ目の引数以下であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  式
     * @return less-than-or-equal 述語
     */
    Predicate le(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * 最初の引数が二つ目の引数以下であるかどうか検証するための述語を作ります。
     * @param x  式
     * @param y  値
     * @return less-than-or-equal 述語
     */
    Predicate le(Expression<? extends Number> x, Number y);
	

    //numerical operations:
	
    /**
     * 引数の算術否定を返す式を作ります。
     * @param x 式
     * @return 算術否定
     */
    <N extends Number> Expression<N> neg(Expression<N> x);

    /**
     * 引数の絶対値を返す式を作ります。
     * @param x 式
     * @return 絶対値
     */
    <N extends Number> Expression<N> abs(Expression<N> x);
	
    /**
     * 引数の和を返す式を作ります。
     * @param x 式
     * @param y 式
     * @return 和
     */
    <N extends Number> Expression<N> sum(Expression<? extends N> x, Expression<? extends N> y);
	
    /**
     * 引数の和を返す式を作ります。
     * @param x 式
     * @param y 値
     * @return 和
     */
    <N extends Number> Expression<N> sum(Expression<? extends N> x, N y);

    /**
     * 引数の和を返す式を作ります。
     * @param x 値
     * @param y 式
     * @return 和
     */
    <N extends Number> Expression<N> sum(N x, Expression<? extends N> y);

    /**
     * 引数の積を返す式を作ります。
     * @param x 式
     * @param y 式
     * @return 積
     */
    <N extends Number> Expression<N> prod(Expression<? extends N> x, Expression<? extends N> y);

    /**
     * 引数の積を返す式を作ります。
     * @param x 式
     * @param y 値
     * @return 積
     */
    <N extends Number> Expression<N> prod(Expression<? extends N> x, N y);

    /**
     * 引数の積を返す式を作ります。
     * @param x 値
     * @param y 式
     * @return 積
     */
    <N extends Number> Expression<N> prod(N x, Expression<? extends N> y);

    /**
     * 引数の差を返す式を作ります。
     * @param x 式
     * @param y 式
     * @return 差
     */
    <N extends Number> Expression<N> diff(Expression<? extends N> x, Expression<? extends N> y);

    /**
     * 引数の差を返す式を作ります。
     * @param x 式
     * @param y 値
     * @return 差
     */
    <N extends Number> Expression<N> diff(Expression<? extends N> x, N y);

    /**
     * 引数の差を返す式を作ります。
     * @param x 値
     * @param y 式
     * @return 差
     */
    <N extends Number> Expression<N> diff(N x, Expression<? extends N> y);
	
    /**
     * 引数の商を返す式を作ります。
     * @param x 式
     * @param y 式
     * @return 商
     */
    Expression<Number> quot(Expression<? extends Number> x, Expression<? extends Number> y);

    /**
     * 引数の商を返す式を作ります。
     * @param x 式
     * @param y 値
     * @return 商
     */
    Expression<Number> quot(Expression<? extends Number> x, Number y);

    /**
     * 引数の商を返す式を作ります。
     * @param x 値
     * @param y 式
     * @return quotient
     */
    Expression<Number> quot(Number x, Expression<? extends Number> y);
	
    /**
     * 引数の剰余を返す式を作ります。
     * @param x 式
     * @param y 式
     * @return 剰余
     */
    Expression<Integer> mod(Expression<Integer> x, Expression<Integer> y);
	
    /**
     * 引数の剰余を返す式を作ります。
     * @param x 式
     * @param y 値
     * @return 剰余
     */
    Expression<Integer> mod(Expression<Integer> x, Integer y);

    /**
     * 引数の剰余を返す式を作ります。
     * @param x 値
     * @param y 式
     * @return 剰余
     */
    Expression<Integer> mod(Integer x, Expression<Integer> y);

    /**
     * 引数の平方根を返す式を作ります。
     * @param x 式
     * @return 平方根
     */	
    Expression<Double> sqrt(Expression<? extends Number> x);

	
    //typecasts:
    
    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;Long&#062;
     */
    Expression<Long> toLong(Expression<? extends Number> number);

    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;Integer&#062;
     */
    Expression<Integer> toInteger(Expression<? extends Number> number);

    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;Float&#062;
     */
    Expression<Float> toFloat(Expression<? extends Number> number);

    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;Double&#062;
     */
    Expression<Double> toDouble(Expression<? extends Number> number);

    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;BigDecimal&#062;
     */
    Expression<BigDecimal> toBigDecimal(Expression<? extends Number> number);

    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param number  数値式
     * @return Expression&#060;BigInteger&#062;
     */
    Expression<BigInteger> toBigInteger(Expression<? extends Number> number);
	
    /**
     * 型変換。　同じ式オブジェクトを返します。
     * @param character 式
     * @return Expression&#060;String&#062;
     */
    Expression<String> toString(Expression<Character> character);

	
    //literals:

    /**
     * リテラルの式を作ります。
     * @param value  式で表される値
     * @return 式リテラル
     * @throws IllegalArgumentException 値がnullの場合
     */
    <T> Expression<T> literal(T value);

    /**
     * 指定された型のnullリテラルの式を作ります。
     * @param resultClass  nullリテラルの型
     * @return null式リテラル
     */
    <T> Expression<T> nullLiteral(Class<T> resultClass);

    //parameters:

    /**
     * パラメーター式を作ります。
     * @param paramClass パラメータークラス
     * @return パラメーター式
     */
    <T> ParameterExpression<T> parameter(Class<T> paramClass);

    /**
     * 与えられた名前のパラメーター式を作ります。
     * @param paramClass パラメータークラス
     * @param name  パラメーターを参照するために使用できる名前
     * @return パラメーター式
     */
    <T> ParameterExpression<T> parameter(Class<T> paramClass, String name);


    //collection operations:
	
    /**
     * コレクションが空かどうかを検証するための述語を作ります。
     *  @param collection 式
     *  @return is-empty 述語
     */
    <C extends Collection<?>> Predicate isEmpty(Expression<C> collection);

    /**
     *  コレクションが空でないかどうかを検証するための述語を作ります。
     *  @param collection 式
     *  @return is-not-empty 述語
     */
    <C extends Collection<?>> Predicate isNotEmpty(Expression<C> collection);

    /**
     * コレクションのサイズを検証するための式を作ります。
     * @param collection 式
     * @return size 式
     */ 
    <C extends java.util.Collection<?>> Expression<Integer> size(Expression<C> collection);
	
    /**
     * コレクションのサイズを検証するための式を作ります。
     * @param collection コレクション
     * @return size 式
     */ 
    <C extends Collection<?>> Expression<Integer> size(C collection);
	
    /**
     * 要素がコレクションのメンバーかどうかを検証するための述語を作ります。
     * コレクションが空の場合、述語はfalseを返します。
     *  @param elem 要素式
     *  @param collection 式
     *  @return is-member述語
     */
    <E, C extends Collection<E>> Predicate isMember(Expression<E> elem, Expression<C> collection);

    /**
     * 要素がコレクションのメンバーかどうかを検証するための述語を作ります。
     * コレクションが空の場合、述語はfalseを返します。
     *  @param elem 要素
     *  @param collection 式
     *  @return is-member 述語
     */
    <E, C extends Collection<E>> Predicate isMember(E elem, Expression<C> collection);

    /**
     * 要素がコレクションのメンバーでないかどうかを検証するための述語を作ります。
     * コレクションが空の場合、述語はtrueを返します。
     *  @param elem 要素式
     *  @param collection 式
     *  @return is-not-member 述語
     */
    <E, C extends Collection<E>> Predicate isNotMember(Expression<E> elem, Expression<C> collection);
	
    /**
     * 要素がコレクションのメンバーでないかどうかを検証するための述語を作ります。
     * コレクションが空の場合、述語はtrueを返します。
     *  @param elem 要素
     *  @param collection 式
     *  @return is-not-member predicate
     */
    <E, C extends Collection<E>> Predicate isNotMember(E elem, Expression<C> collection);


    //get the values and keys collections of the Map, which may then
    //be passed to size(), isMember(), isEmpty(), etc

    /**
     * Mapの値を返す式を作ります。
     * @param map  map
     * @return コレクション式
     */
    <V, M extends Map<?, V>> Expression<Collection<V>> values(M map);

    /**
     * Mapのキーを返す式を作ります。
     * @param map  map
     * @return set 式
     */
    <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map);

	
    //string functions:
	
    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @return like 述語
     */
    Predicate like(Expression<String> x, Expression<String> pattern);
	
    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @return like 述語
     */
    Predicate like(Expression<String> x, String pattern);
	
    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @param escapeChar  エスケープ文字式
     * @return like 述語
     */
    Predicate like(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);
	
    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @param escapeChar  エスケープ文字
     * @return like 述語
     */
    Predicate like(Expression<String> x, Expression<String> pattern, char escapeChar);
	
    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @param escapeChar  エスケープ文字式
     * @return like 述語
     */
    Predicate like(Expression<String> x, String pattern, Expression<Character> escapeChar);

    /**
     * 式が与えられたパターンを満たすかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @param escapeChar  エスケープ文字
     * @return like 述語
     */
    Predicate like(Expression<String> x, String pattern, char escapeChar);
	
    /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern);
	
    /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, String pattern);

    /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @param escapeChar  エスケープ文字式
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar);

    /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列式
     * @param escapeChar  エスケープ文字
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, Expression<String> pattern, char escapeChar);

    /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @param escapeChar  エスケープ文字式
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, String pattern, Expression<Character> escapeChar);
	
   /**
     * 式が与えられたパターンを満たさないかどうかを検証する述語を作ります。
     * @param x  文字列式
     * @param pattern  文字列 
     * @param escapeChar  エスケープ文字
     * @return not-like 述語
     */
    Predicate notLike(Expression<String> x, String pattern, char escapeChar);

    /**
     * 文字列の結合のための式を作ります。
     *  @param x  文字列式
     *  @param y  文字列式
     *  @return 結合に関連した式
     */
    Expression<String> concat(Expression<String> x, Expression<String> y);
	
    /**
     *  文字列の結合のための式を作ります。
     *  @param x  文字列式
     *  @param y  文字列 
     *  @return 結合に関連した式
     */
    Expression<String> concat(Expression<String> x, String y);

    /**
     *  文字列の結合のための式を作ります。
     *  @param x  文字列 
     *  @param y  文字列式
     *  @return 結合に関連した式
     */
    Expression<String> concat(String x, Expression<String> y);
	
    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring starting at the specified position
     *  through to end of the string.
     *  First position is 1.
     *  @param x  文字列式
     *  @param from  start position expression 
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, Expression<Integer> from);
	
    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring starting at the specified position
     *  through to end of the string.
     *  First position is 1.
     *  @param x  文字列式
     *  @param from  start position 
     *  @return expression corresponding to substring extraction
     */
    Expression<String> substring(Expression<String> x, int from);

    /**
     *  Create an expression for substring extraction.
     *  Extracts a substring of given length starting at the
     *  specified position.
     *  First position is 1.
     *  @param x  文字列式
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
     *  @param x  文字列式
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
     * @param x  文字列式
     * @return expression to convert to lowercase
     */
    Expression<String> lower(Expression<String> x);
	
    /**
     * Create expression for converting a string to uppercase.
     * @param x  文字列式
     * @return expression to convert to uppercase
     */
    Expression<String> upper(Expression<String> x);
	
    /**
     * Create expression to return length of a string.
     * @param x  文字列式
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
     * @param x 式
     * @param y 式
     * @return coalesce expression
     */
    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Expression<? extends Y> y);

    /**
     * Create an expression that returns null if all its arguments
     * evaluate to null, and the value of the first non-null argument
     * otherwise.
     * @param x 式
     * @param y 値
     * @return coalesce expression
     */
    <Y> Expression<Y> coalesce(Expression<? extends Y> x, Y y);
    
    /**
     * Create an expression that tests whether its argument are
     * equal, returning null if they are and the value of the
     * first expression if they are not.
     * @param x 式
     * @param y 式
     * @return nullif expression
     */
    <Y> Expression<Y> nullif(Expression<Y> x, Expression<?> y);

    /**
     * Create an expression that tests whether its argument are
     * equal, returning null if they are and the value of the
     * first expression if they are not.
     * @param x 式
     * @param y 値
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
		 * case式にwhen/then句を追加します。
		 * @param condition  "when" 条件
		 * @param result  "then"結果式
		 * @return 一般的なcase式
		 */
		Case<R> when(Expression<Boolean> condition, R result);

		/**
         * case式にwhen/then句を追加します。
		 * @param condition  "when" 条件
		 * @param result  "then"結果式
		 * @return 一般的なcase式
		 */
		Case<R> when(Expression<Boolean> condition, Expression<? extends R> result);

		/**
		 * case式に"else"句を追加します。
		 * @param result  "else" 結果
		 * @return 式
		 */
		Expression<R> otherwise(R result);

		/**
         * case式に"else"句を追加します。
		 * @param result  "else" 結果式
		 * @return 式
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




