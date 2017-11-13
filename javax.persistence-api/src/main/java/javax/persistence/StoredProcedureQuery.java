/*******************************************************************************
 * Copyright (c) 2011 - 2017 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/ 
package javax.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ストアドプロシージャーのクエリー実行を制御するために使用されるインターフェイスです。
 *
 * <p>
 * ストアドプロシージャーのクエリーの実行は、以下のように制御されるでしょう。
 * <ul>
 * <li><code>setParameter</code>メソッドはすべての必須な<code>IN</code>パラメーターと<code>INOUT</code>パラメーターの値を設定するために使用されます。
 * ストアドプロシージャーによってデフォルト値が定義されているストアドプロシージャーのパラメーターの値を設定する必要はありません。</li>
 * <li>
 * <code>StoredProcedureQuery</code>オブジェクトの<code>getResultList</code>や<code>getSingleResult</code>が呼び出されると、
 * 実行されていないストアドプロシージャーのクエリーに対して
 * プロバイダは<code>getResultList</code>または<code>getSingleResult</code>を処理する前に、
 * <code>execute</code>を呼び出すでしょう。</li>
 * <li>
 * <code>StoredProcedureQuery</code>オブジェクトの<code>executeUpdate</code>が呼び出されると、
 * プロバイダは実行されていないストアドプロシージャーの<code>execute</code>の実行に続いて<code>getUpdateCount</code>を実行します。
 * <code>executeUpdate</code>の結果は<code>getUpdateCount</code>の結果になるでしょう。</li>
 * <li>
 * <code>execute</code>メソッドは、最も一般的なケース
 * (複数の結果セットおよび/または更新カウント、場合によっては出力パラメータ値とも組み合わされる)だけでなく、
 * スカラー結果が<code>INOUT</code>および<code>OUT</code>パラメータを介して戻される単純なケースもサポートします。</li>
 * <li>
 * <code>execute</code>メソッドは、最初の結果が結果セットの場合はtrueを返し、
 * 更新カウントの場合や、<code>INOUT</code>および<code>OUT</code>パラメーターがあったとしても、それ以外の結果がない場合はfalseを返します。</li>
 * <li>
 * <code>execute</code>メソッドがtrueを返した場合、保留された結果セットは<code>getResultList</code>や<code>getSingleResult</code>を呼び出すことで取得できます。</li>
 * <li>
 * <code>hasMoreResults</code>メソッドはさらに結果があるかどうかの確認に使用することができます。</li>
 * <li>
 * <code>execute</code>または<code>hasMoreResults</code>がfalseを返した場合、
 * <code>getUpdateCount</code>メソッドを呼び出して、更新カウントの場合は保留中の結果を取得できます。
 * <code>getUpdateCount</code>メソッドは更新カウント(ゼロ以上)を、更新カウントが存在しない場合(つまり、次の結果が結果セットである場合、もしくは次の更新カウントがない場合)は-1を返します。</li>
 * <li>
 * 移植性のためには<code>INOUT</code>や<code>OUT</code>パラメータの値が抽出される前に、
 * JDBC結果セットおよび更新カウントに対応する結果を処理する必要があります。</li>
 * <li>
 * <code>getResultList</code>や<code>getUpdateCount</code>で返された結果が枯渇した後、
 * <code>INOUT</code>および<code>OUT</code>パラメーターによって返される結果を取得できます。</li>
 * <li>
 * <code>getOutputParameterValue</code>メソッドは、プロシージャーから<code>INOUT</code>および<code>OUT</code>パラメーターを介して戻された値を取得するために使用されます。</li>
 * <li>
 * 結果セットに<code>REF_CURSOR</code>パラメータを使用する場合、<code>getResultList</code>を呼び出して結果セットを取得する前に更新カウントを枯渇させる必要があります。
 * 代わりに<code>REF_CURSOR</code>結果セットは<code>getOutputParameterValue</code>を使用して取得できます。
 * 結果セットのマッピングは<code>REF_CURSOR</code>パラメーターがクエリに登録された順序で<code>REF_CURSOR</code>パラメーターに対応する結果に適用されます。</li>
 * <li>
 * 結果が<code>INOUT</code>および<code>OUT</code>パラメータを介してのみ返される最も単純なケースでは、
 * <code>execute</code>に続けてすぐに<code>getOutputParameterValue</code>を呼び出すことができます。</li>
 * </ul>
 *
 * @see Query
 * @see Parameter
 *
 * @since Java Persistence 2.1
 */
public interface StoredProcedureQuery extends Query {

    /**
     * クエリーのプロパティもしくはヒントを設定します。
     * 
     * ヒント要素はクエリーのプロパティとヒントを指定するために使用できます。
     * この仕様で定義されているプロパティはプロバイダによって監視されなければなりません。
     * プロバイダによって認識されないベンダー固有のヒントは、暗黙のうちに無視されなければなりません。
     * ポータブルアプリケーションは標準のタイムアウトのヒントに頼るべきではありません。
     * 使用されているデータベースおよびプロバイダによっては、このヒントが監視されるかどうかはわかりません。
     * @param hintName  プロパティもしくはヒントの名前
     * @param value  プロパティもしくはヒントのための値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 二つ目の引数が実装に適合しない場合
     */
    StoredProcedureQuery setHint(String hintName, Object value);

    /**
     * <code>Parameter</code>オブジェクトの値をバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException クエリーのパラメーターに対応するパラメーターが存在しない場合
     */
    <T> StoredProcedureQuery setParameter(Parameter<T> param, 
                                          T value);

    /**
     * <code>Parameter</code>オブジェクトに<code>java.util.Calendar</code>のインスタンスをバインドします。
     * @param param パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException クエリーのパラメーターに対応するパラメーターが存在しない場合
     */
    StoredProcedureQuery setParameter(Parameter<Calendar> param,
                                      Calendar value, 
                                      TemporalType temporalType);

    /**
     * <code>Parameter</code>オブジェクトに<code>java.util.Date</code>のインスタンスをバインドします。
     * @param param パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException クエリーのパラメーターに対応するパラメーターが存在しない場合
     */
    StoredProcedureQuery setParameter(Parameter<Date> param, 
                                      Date value, 
                                      TemporalType temporalType);

    /**
     * 名前付きパラメーターに引数の値をバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名に適合するパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(String name, Object value);

    /**
     * 名前付きパラメーターに<code>java.util.Calendar</code>のインスタンスをバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名に適合するパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(String name, 
                                      Calendar value, 
                                      TemporalType temporalType);

    /**
     * 名前付きパラメーターに<code>java.util.Date</code>のインスタンスをバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名に適合するパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(String name, 
                                      Date value, 
                                      TemporalType temporalType);

    /**
     * 位置指定のパラメーターに引数の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 位置に適合する位置指定のパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(int position, Object value);

    /**
     * 位置指定のパラメーターに<code>java.util.Calendar</code>のインスタンスをバインドします。
     * @param position  位置
     * @param value パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 位置に適合する位置指定のパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(int position, 
                                      Calendar value,  
                                      TemporalType temporalType);

    /**
     * 位置指定のパラメーターに<code>java.util.Date</code>のインスタンスをバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 位置に適合する位置指定のパラメーターがクエリーに存在しないか、引数が不正な型の場合
     */
    StoredProcedureQuery setParameter(int position, 
                                      Date value,  
                                      TemporalType temporalType);

    /**
      * クエリーの実行に使用されるフラッシュモードタイプを設定します。
      *
      * フラッシュモードタイプはエンティティマネージャーで使用中のフラッシュモードタイプと関係なくクエリーに適用されます。
     * @param flushMode  フラッシュモード
     * @return 同じクエリーのインスタンス
     */
    StoredProcedureQuery setFlushMode(FlushModeType flushMode);

    /**
     * 位置指定のパラメーターのを登録します。
     * 
     * すべてのパラメーターを登録する必要があります。
     * @param position  パラメーターの位置
     * @param type  パラメーターの型
     * @param mode  パラメーターの形式
     * @return 同じクエリーのインスタンス
     */
    StoredProcedureQuery registerStoredProcedureParameter(
	  int position,
	  Class type,
	  ParameterMode mode);

    /**
     * 名前付きパラメーターを登録します。
     * @param parameterName  登録されているかメタデータで指定されているパラメーターの名前
     * @param type  パラメーターの型
     * @param mode  パラメーターの形式
     * @return 同じクエリーのインスタンス
     */
    StoredProcedureQuery registerStoredProcedureParameter(
	  String parameterName,
	  Class type,
	  ParameterMode mode);

    /**
     *  Retrieve a value passed back from the procedure
     *  through an INOUT or OUT parameter.
     *  For portability, all results corresponding to result sets
     *  and update counts must be retrieved before the values of 
     *  output parameters.
     *  @param position  parameter position
     *  @return the result that is passed back through the parameter
     *  @throws IllegalArgumentException if the position does
     *          not correspond to a parameter of the query or is
     *          not an INOUT or OUT parameter
     */
    Object getOutputParameterValue(int position);

    /**
     *  Retrieve a value passed back from the procedure
     *  through an INOUT or OUT parameter.
     *  For portability, all results corresponding to result sets
     *  and update counts must be retrieved before the values of 
     *  output parameters.
     *  @param parameterName  name of the parameter as registered or
     *              specified in metadata
     *  @return the result that is passed back through the parameter
     *  @throws IllegalArgumentException if the parameter name does
     *          not correspond to a parameter of the query or is
     *          not an INOUT or OUT parameter
     */
    Object getOutputParameterValue(String parameterName);

    /**
     * Return true if the first result corresponds to a result set,
     * and false if it is an update count or if there are no results
     * other than through INOUT and OUT parameters, if any.
     * @return 最初の結果が結果セットに関連する場合はtrue
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    boolean execute();

    /**
     * Return the update count of -1 if there is no pending result or
     * if the first result is not an update count.  The provider will
     * call <code>execute</code> on the query if needed.
     * @return 更新件数か、保留している結果が存在しない場合や次の結果が更新件数でない場合は-1
     * @throws TransactionRequiredException トランザクションが存在しない場合、
     * または永続性コンテキストがトランザクションに参加していない場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合 
     */
    int executeUpdate();

    /**
     * Retrieve the list of results from the next result set.
     * The provider will call <code>execute</code> on the query
     * if needed.
     * A <code>REF_CURSOR</code> result set, if any, will be retrieved
     * in the order the <code>REF_CURSOR</code> parameter was 
     * registered with the query.
     * @return 結果のリスト、次のアイテムが結果セットでない場合はnull
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    List getResultList();

    /**
     * Retrieve a single result from the next result set.
     * The provider will call <code>execute</code> on the query
     * if needed.
     * A <code>REF_CURSOR</code> result set, if any, will be retrieved
     * in the order the <code>REF_CURSOR</code> parameter was 
     * registered with the query.
     * @return the result or null if the next item is not a result set
     * @throws NoResultException 次の結果セットに結果が存在しない場合
     * @throws NonUniqueResultException 二つ以上の結果が存在した場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    Object getSingleResult();

    /**
     * Return true if the next result corresponds to a result set,
     * and false if it is an update count or if there are no results
     * other than through INOUT and OUT parameters, if any.
     * @return  true if next result corresponds to result set
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    boolean hasMoreResults();

    /**
     * 更新件数を返すか、保留している結果が存在しない場合や次の結果が更新件数でない場合は-1を返します。
     * @return  更新件数か、保留している結果が存在しない場合や次の結果が更新件数でない場合は-1
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    int getUpdateCount();

}
