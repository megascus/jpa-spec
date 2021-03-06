/*******************************************************************************
 * Copyright (c) 2008 - 2017 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Lukas Jungmann  - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.stream.Stream;

/**
 * クエリー実行のコントロールに使用するインターフェースです。
 *
 * @see TypedQuery
 * @see StoredProcedureQuery
 * @see Parameter
 *
 * @since Java Persistence 1.0
 */
public interface Query {

    /**
     * SELECTクエリーを実行し、問合せ結果を型のないリストとして返します。
     * @return 結果のリスト
     * @throws IllegalStateException Java Persistenceクエリー言語のUPDATEまたはDELETE文で呼び出された場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws TransactionRequiredException <code>NONE</code>以外のロックモードが設定されトランザクションが存在しない場合、
     * または永続化コンテキストがトランザクションに参加していない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされる場合
     * @throws LockTimeoutException 悲観ロックに失敗し、そのステートメントのみロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    List getResultList();

    /**
     * SELECTクエリーを実行し、問合せ結果を型のない<code>java.util.stream.Stream</code>として戻します。
     * 
     * デフォルトでは、このメソッドは<code>getResultList().stream()</code>に委譲しますが、
     * 永続化プロバイダはこのメソッドをオーバーライドして追加の機能を提供することもできます。
     *
     * @return 結果のストリーム
     * @throws IllegalStateException Java Persistenceクエリー言語のUPDATEまたはDELETE文で呼び出された場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws TransactionRequiredException <code>NONE</code>以外のロックモードが設定されトランザクションが存在しない場合、
     * または永続化コンテキストがトランザクションに参加していない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされる場合
     * @throws LockTimeoutException 悲観ロックに失敗し、そのステートメントのみロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     * @see Stream
     * @see #getResultList()
     * @since 2.2
     */
    default Stream getResultStream() {
        return getResultList().stream();
    }

    /**
     * 一つの型のない結果を返すSELECTクエリーを実行します。
     * @return 結果
     * @throws NoResultException 結果がなかった場合
     * @throws NonUniqueResultException 2つ以上の結果があった場合
     * @throws IllegalStateException Java Persistenceクエリー言語のUPDATEまたはDELETE文で呼び出された場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws TransactionRequiredException <code>NONE</code>以外のロックモードが設定されトランザクションが存在しない場合、
     * または永続化コンテキストがトランザクションに参加していない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされる場合
     * @throws LockTimeoutException 悲観ロックに失敗し、そのステートメントのみロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    Object getSingleResult();

    /**
     * 更新または削除ステートメントを実行します。
     * @return エンティティが更新や削除された数
     * @throws IllegalStateException Java Persistenceクエリー言語のSELECT文もしくはクライテリアクエリーで呼び出された場合
     * @throws TransactionRequiredException トランザクションが存在しない場合、
     * または永続化コンテキストがトランザクションに参加していない場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    int executeUpdate();

    /**
     * 取得する結果の最大件数を設定します。
     * @param maxResult  取得する結果の最大件数
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 引数が負の数の場合
     */
    Query setMaxResults(int maxResult);

    /**
     * クエリーオブジェクトが取得するように設定された結果の最大件数。
     * 
     * <code>setMaxResults</code>がクエリーオブジェクトで呼び出されていなかった場合は<code>Integer.MAX_VALUE</code>を返します。
     * @return 結果の最大件数
     * @since Java Persistence 2.0
     */
    int getMaxResults();

    /**
     * 検索結果を取得する最初のポジションを設定します。
     * @param startPosition 検索結果の0から始まる最初のポジション
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 引数が負数だった場合
     */
    Query setFirstResult(int startPosition);

    /**
     * クエリーオブジェクトが取得するように設定された結果の最初のポジション。
     * 
     * <code>setFirstResult</code>がクエリーオブジェクトで呼び出されていなかった場合は0を返します。
     * @return 結果の最初のポジション
     * @since Java Persistence 2.0
     */
    int getFirstResult();

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
    Query setHint(String hintName, Object value);

    /**
     * クエリーインスタンスで有効なプロパティとヒントと関連する値を取得します。
     * @return クエリーのプロパティとヒント
     * @since Java Persistence 2.0
     */
    Map<String, Object> getHints();

    /**
     * <code>Parameter</code>オブジェクトの値をバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     * @since Java Persistence 2.0
     */
    <T> Query setParameter(Parameter<T> param, T value);

    /**
     * <code>java.util.Calendar</code>のインスタンスを<code>Parameter</code>オブジェクトにバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     * @since Java Persistence 2.0
     */
    Query setParameter(Parameter<Calendar> param, Calendar value, 
                       TemporalType temporalType);

    /**
     * <code>java.util.Date</code>のインスタンスを<code>Parameter</code>オブジェクトにバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     * @since Java Persistence 2.0
     */
    Query setParameter(Parameter<Date> param, Date value, 
                       TemporalType temporalType);

    /**
     * 引数の値を名前付きパラメーターにバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(String name, Object value);

    /**
     * <code>java.util.Calendar</code>のインスタンスを名前付きパラメーターにバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(String name, Calendar value, 
                       TemporalType temporalType);

    /**
     * <code>java.util.Date</code>のインスタンスを名前付きパラメーターにバインドします。
     * @param name   パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(String name, Date value, 
                       TemporalType temporalType);

    /**
     * 位置指定のパラメーターに引数の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(int position, Object value);

    /**
     * 位置指定のパラメーターに<code>java.util.Calendar</code>の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(int position, Calendar value,  
                       TemporalType temporalType);

    /**
     * 位置指定のパラメーターに<code>java.util.Date</code>の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    Query setParameter(int position, Date value,  
                       TemporalType temporalType);

    /**
     * クエリーの宣言されたパラメーターに対応するパラメーターオブジェクトを取得します。
     * 
     * クエリーにパラメーターがない場合は空のセットを返します。
     * このメソッドはネイティブクエリーではサポートされない場合があります。
     * @return パラメーターオブジェクトのSet
     * @throws IllegalStateException 実装がこの使用をサポートしていないネイティブクエリーで呼び出された場合
     * @since Java Persistence 2.0
     */
    Set<Parameter<?>> getParameters();

    /**
     * 与えられた名前の宣言されたパラメーターに対応するパラメーターオブジェクトを取得します。 
     * 
     * このメソッドはネイティブクエリーではサポートされない場合があります。
     * @param name  パラメーターの名前
     * @return パラメーターオブジェクト
     * @throws IllegalArgumentException 指定した名前のパラメーターが存在しない場合
     * @throws IllegalStateException 実装がこの使用をサポートしていないネイティブクエリーで呼び出された場合
     * @since Java Persistence 2.0
     */
    Parameter<?> getParameter(String name);

    /**
     * 与えられた名前と型の宣言されたパラメーターに対応するパラメーターオブジェクトを取得します。 
     * 
     * このメソッドはクライテリアクエリー以外ではサポートされない場合があります。
     * @param name  パラメーターの名前
     * @param type  型
     * @return パラメーターオブジェクト
     * @throws IllegalArgumentException 指定した名前のパラメーターが存在しないか、型が割り当て不能な場合
     * @throws IllegalStateException 実装がこの使用をサポートしてないネイティブクエリーかJPQLクエリーで呼び出された場合
     * @since Java Persistence 2.0
     */
    <T> Parameter<T> getParameter(String name, Class<T> type);

    /**
     * 宣言された位置のパラメーターに対応するパラメータオブジェクトを指​​定された位置で取得します。
     * 
     * このメソッドはネイティブクエリーではサポートされない場合があります。
     * @param position  位置
     * @return パラメーターオブジェクト
     * @throws IllegalArgumentException 指定した位置のパラメーターが存在しない場合
     * @throws IllegalStateException 実装がこの使用をサポートしていないネイティブクエリーで呼び出された場合
     * @since Java Persistence 2.0
     */
    Parameter<?> getParameter(int position);

    /**
     * 宣言された位置のパラメーターに対応するパラメータオブジェクトを指​​定された位置と型で取得します。
     * 
     * このメソッドはプロバイダによってはサポートされない場合があります。
     * @param position  位置
     * @param type  型
     * @return パラメーターオブジェクト
     * @throws IllegalArgumentException 指定した位置のパラメーターが存在しないか、型が割り当て不能な場合
     * @throws IllegalStateException 実装がこの使用をサポートしてないネイティブクエリーかJPQLクエリーで呼び出された場合
     * @since Java Persistence 2.0
     */
    <T> Parameter<T> getParameter(int position, Class<T> type);

    /**
     * 値がパラメータにバインドされているかどうかを示すbooleanを返します。
     * @param param パラメーターオブジェクト
     * @return パラメータがバインドされているかどうかを示すboolean
     * @since Java Persistence 2.0
     */
    boolean isBound(Parameter<?> param);

    /**
     * パラメーターにバインドされた入力値を返します。 
     * 
     * (注意：OUTパラメータはバインドされていません。)
     * @param param パラメーターオブジェクト
     * @return パラメーターの値
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターでない場合
     * @throws IllegalStateException パラメーターがバインドされてない場合
     * @since Java Persistence 2.0
     */
    <T> T getParameterValue(Parameter<T> param);

    /**
     * 名前付きパラメーターにバインドされた入力値を返します。 
     * 
     * (注意：OUTパラメータはバインドされていません。)
     * @param name  パラメーターの名前
     * @return パラメーターの値
     * @throws IllegalStateException パラメーターがバインドされてない場合
     * @throws IllegalArgumentException 指定された名前のパラメーターが存在しない場合
     * @since Java Persistence 2.0
     */
    Object getParameterValue(String name);

    /**
     * 位置パラメーターにバインドされた入力値を返します。 
     * 
     * (注意：OUTパラメータはバインドされていません。)
     * @param position  位置
     * @return パラメーターの値
     * @throws IllegalStateException パラメーターがバインドされてない場合
     * @throws IllegalArgumentException 指定された位置にパラメーターが存在しない場合
     * @since Java Persistence 2.0
     */
    Object getParameterValue(int position);

    /**
      * クエリーの実行に使用されるフラッシュモードタイプを設定します。
      *
      * フラッシュモードタイプはエンティティマネージャーで使用中のフラッシュモードタイプと関係なくクエリーに適用されます。
      * @param flushMode  フラッシュモード
      * @return 同じクエリーのインスタンス
     */
    Query setFlushMode(FlushModeType flushMode);

    /**
     * クエリーの実行に有効なフラッシュモードを取得します。
     * 
     * クエリーオブジェクトにフラッシュモードが設定されていない場合は、エンティティマネージャーで有効なフラッシュモードを返します。
     * @return フラッシュモード
     * @since Java Persistence 2.0
     */
    FlushModeType getFlushMode();

    /**
      * クエリーの実行に使用されるロックモードタイプを設定します。
      * @param lockMode  ロックモード
      * @return 同じクエリーのインスタンス
      * @throws IllegalStateException クエリーがJava Persistenceクエリー言語のSELECTクエリーかCriteriaQueryクエリーでないと判明した場合
     * @since Java Persistence 2.0
     */
    Query setLockMode(LockModeType lockMode);

    /**
     * クエリーの現在のロックモードを取得します。
     * 
     * nullが返ってくる場合はクエリーオブジェクトにロックモードが設定されていません。
     * @return ロックモード
     * @throws IllegalStateException クエリーがJava Persistenceクエリー言語のSELECTクエリーかCriteriaQueryクエリーでないと判明した場合
     * @since Java Persistence 2.0
     */
    LockModeType getLockMode();

    /**
     * プロバイダ固有のAPIにアクセスできるように、指定された型のオブジェクトを返します。
     * 
     * プロバイダのクエリー実装が指定されたクラスをサポートしていない場合、 <code>PersistenceException</code>が投げられます。
     * @param cls  返されるオブジェクトのクラス、通常だと基礎となるクエリーの実装クラスか実装するインターフェイスのいずれかです。
     * @return 指定されたクラスのインスタンス
     * @throws PersistenceException プロバイダが呼び出しをサポートしない場合
     * @since Java Persistence 2.0
     */
    <T> T unwrap(Class<T> cls);
}
