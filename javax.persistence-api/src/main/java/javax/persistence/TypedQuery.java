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

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.stream.Stream;

/**
 * 型付きクエリーの実行を制御するためのインタフェースです。
 * 
 * @param <X> クエリーの結果の型
 *
 * @see Query
 * @see Parameter
 *
 * @since Java Persistence 2.0
 */
public interface TypedQuery<X> extends Query {
	
    /**
     * SELECTクエリーを実行し、問合せ結果を型付きリストとして返します。
     * @return 結果のリスト
     * @throws IllegalStateException Java Persistenceクエリー言語のUPDATEまたはDELETE文で呼び出された場合
     * @throws QueryTimeoutException クエリーの実行がクエリーの設定されたタイムアウト値を超え、そのステートメントだけがロールバックされる場合
     * @throws TransactionRequiredException <code>NONE</code>以外のロックモードが設定されトランザクションが存在しない場合、
     * または永続化コンテキストがトランザクションに参加していない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされる場合
     * @throws LockTimeoutException 悲観ロックに失敗し、そのステートメントのみロールバックされる場合
     * @throws PersistenceException クエリーの実行がクエリーの設定されたタイムアウト値を超え、トランザクションがロールバックされる場合
     */
    List<X> getResultList();

    /**
     * SELECTクエリーを実行し、問合せ結果を型付き<code>java.util.stream.Stream</code>として戻します。
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
    default Stream<X> getResultStream() {
        return getResultList().stream();
    }

    /**
     * 一つの結果を返すSELECTクエリーを実行します。
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
    X getSingleResult();

    /**
     * 検索結果の最大件数を設定します。
     * @param maxResult 検索結果の最大件数
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 引数が負数だった場合
     */
    TypedQuery<X> setMaxResults(int maxResult);

    /**
     * 検索結果の最初のポジションを設定します。
     * @param startPosition 検索結果の0から始まる最初のポジション
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 引数が負数だった場合
     */
    TypedQuery<X> setFirstResult(int startPosition);

    /**
     * クエリーのプロパティもしくはヒントを設定します。
     * 
     * ヒント要素はクエリーのプロパティとヒントを指定するために使用できます。
     * この仕様で定義されているプロパティはプロバイダによって監視されなければなりません。
     * プロバイダによって認識されないベンダー固有のヒントは、暗黙のうちに無視されなければなりません。
     * ポータブルアプリケーションは標準のタイムアウトのヒントに頼るべきではありません。
     * 使用されているデータベースおよびプロバイダが使用しているロックメカニズムによっては、このヒントが監視されるかどうかはわかりません。
     * @param hintName  プロパティもしくはヒントの名前
     * @param value  プロパティもしくはヒントのための値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException 二つ目の引数が実装に適合しない場合
     */
    TypedQuery<X> setHint(String hintName, Object value);

    /**
     * <code>Parameter</code>オブジェクトの値をバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     */
     <T> TypedQuery<X> setParameter(Parameter<T> param, T value);

    /**
     * <code>java.util.Calendar</code>のインスタンスを<code>Parameter</code>オブジェクトにバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     */
    TypedQuery<X> setParameter(Parameter<Calendar> param, 
                               Calendar value,  
                               TemporalType temporalType);

    /**
     * <code>java.util.Date</code>のインスタンスを<code>Parameter</code>オブジェクトにバインドします。
     * @param param  パラメーターオブジェクト
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーターがクエリーのパラメーターに対応しない場合
     */
    TypedQuery<X> setParameter(Parameter<Date> param, Date value,  
                               TemporalType temporalType);

    /**
     * 引数の値を名前付きパラメーターにバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(String name, Object value);

    /**
     * <code>java.util.Calendar</code>のインスタンスを名前付きパラメーターにバインドします。
     * @param name  パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(String name, Calendar value, 
                               TemporalType temporalType);

    /**
     * <code>java.util.Date</code>のインスタンスを名前付きパラメーターにバインドします。
     * @param name   パラメーターの名前
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException パラメーター名がクエリーのパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(String name, Date value, 
                               TemporalType temporalType);

    /**
     * 位置指定のパラメーターに引数の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(int position, Object value);

    /**
     * 位置指定のパラメーターに<code>java.util.Calendar</code>の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(int position, Calendar value,  
                               TemporalType temporalType);

    /**
     * 位置指定のパラメーターに<code>java.util.Date</code>の値をバインドします。
     * @param position  位置
     * @param value  パラメーターの値
     * @param temporalType  時制の型
     * @return 同じクエリーのインスタンス
     * @throws IllegalArgumentException positionがクエリーの位置指定のパラメーターに対応しない場合、もしくは引数が不正な型の場合
     */
    TypedQuery<X> setParameter(int position, Date value,  
                               TemporalType temporalType);

     /**
      * クエリーの実行に使用されるフラッシュモードタイプを設定します。
      *
      * フラッシュモードタイプはエンティティマネージャーで使用中のフラッシュモードタイプと関係なくクエリーに適用されます。
      * @param flushMode  フラッシュモード
      * @return 同じクエリーのインスタンス
      */
     TypedQuery<X> setFlushMode(FlushModeType flushMode);

     /**
      * クエリーの実行に使用されるロックモードタイプを設定します。
      * @param lockMode  ロックモード
      * @return 同じクエリーのインスタンス
      * @throws IllegalStateException クエリーがJava Persistenceクエリー言語のSELECTクエリーかCriteriaQueryクエリーでないと判明した場合
      */
     TypedQuery<X> setLockMode(LockModeType lockMode);

}
