/*******************************************************************************
 * Copyright (c) 2008 - 2014 Oracle Corporation. All rights reserved.
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
package javax.persistence;

/**
 * ロックモードはロックを取る{@link javax.persistence.EntityManager}メソッドの1つ(<code>lock</code>や<code>find</code>、<code>refresh</code>)または{@link Query#setLockMode Query.setLockMode()}、{@link TypedQuery#setLockMode TypedQuery.setLockMode()}メソッドのいずれかに<code>LockModeType</code>引数を渡すことによって指定できます。
 * 
 * <p> ロックモードは楽観ロック、悲観ロックのどちらかを指定するのに使用されます。
 *
 * <p> 楽観ロックは{@link LockModeType#OPTIMISTIC LockModeType.OPTIMISTIC}および{@link LockModeType#OPTIMISTIC_FORCE_INCREMENT LockModeType.OPTIMISTIC_FORCE_INCREMENT}
 * を使用して指定されます。
 * ロックモードの値{@link LockModeType#READ LockModeType.READ}および{@link LockModeType#WRITE LockModeType.WRITE}はそれぞれ<code>OPTIMISTIC</code>
 * および<code>OPTIMISTIC_FORCE_INCREMENT</code>と同じ意味です。
 * 新しいアプリケーションでは後者を使用する方が好ましいでしょう。
 *
 * <p> <code>LockModeType.OPTIMISTIC</code>および<code>LockModeType.OPTIMISTIC_FORCE_INCREMENT</code>のロックが要求された時の意味は次のとおりです。
 *
 * <p> トランザクションT1がバージョン管理されたオブジェクトで<code>LockModeType.OPTIMISTIC</code>型のロックを呼び出したした場合、
 * エンティティマネージャーは次のいずれの現象も起こらないようにする必要があります。
 * <ul>
 *   <li> P1 (ダーティーリード): トランザクションT1が行を変更し、別のトランザクションT2がその後にその行を読み取り、
 * T1がコミットまたはロールバックされる前に変更された値を取得します。
 * トランザクションT2は最終的に正常にコミットされます。
 * T1がコミットするかロールバックするか、T1が終了するのがT2がコミットする前か後かは問いません。
 *   </li>
 *   <li> P2 (ノンリピータブルリード): トランザクションT1が行を読み取り、T1がコミットされる前に別のトランザクションT2がその行を変更または削除します。
 * 両方のトランザクションは最終的に正常にコミットされます。
 *   </li>
 * </ul>
 *
 * <p> ロックモードはP1とP2の両方の現象を防止する必要があります。
 *
 * <p> さらに、<code>LockModeType.OPTIMISTIC_FORCE_INCREMENT</code>がバージョン管理されたオブジェクトで呼び出された場合は、
 * エンティティのバージョン列が強制的に更新(インクリメント)されます。
 *
 * <p> JPAの実装ではバージョン管理されないオブジェクトに対する楽観ロックモードのサポートは必須ではありません。
 * そのようなロックの呼び出しをサポートしていない場合は{@link PersistenceExceptionを投げる必要があります。
 *
 * <p> ロックモード{@link LockModeType#PESSIMISTIC_READ LockModeType.PESSIMISTIC_READ}および{@link LockModeType#PESSIMISTIC_WRITE LockModeType.PESSIMISTIC_WRITE}、
 * {@link LockModeType#PESSIMISTIC_FORCE_INCREMENT LockModeType.PESSIMISTIC_FORCE_INCREMENT}は長期のデータベースロックを即時に取得するために使用されます。
 *
 * <p> <code>LockModeType.PESSIMISTIC_READ</code>および<code>LockModeType.PESSIMISTIC_WRITE</code>、
 * <code>LockModeType.PESSIMISTIC_FORCE_INCREMENT</code>のロックが要求された時の意味は次のとおりです。
 *
 * <p> トランザクションT1がオブジェクトで<code>LockModeType.PESSIMISTIC_READ</code>もしくは<code>LockModeType.PESSIMISTIC_WRITE</code>型のロックを呼び出したした場合、
 * エンティティマネージャーは次のいずれの現象も起こらないようにする必要があります。
 * <ul> 
 * <li> P1 (ダーティーリード): トランザクションT1が行を変更し、別のトランザクションT2がその後にその行を読み取り、
 * T1がコミットまたはロールバックされる前に変更された値を取得します。
 *
 * <li> P2 (ノンリピータブルリード): トランザクションT1が行を読み取り、T1がコミットもしくはロールバックされる前に別のトランザクションT2がその行を変更または削除します。
 * </ul>
 *
 * <p> A lock with <code>LockModeType.PESSIMISTIC_WRITE</code> can be obtained on
 * an entity instance to force serialization among transactions
 * attempting to update the entity data. A lock with
 * <code>LockModeType.PESSIMISTIC_READ</code> can be used to query data using
 * repeatable-read semantics without the need to reread the data at
 * the end of the transaction to obtain a lock, and without blocking
 * other transactions reading the data. A lock with
 * <code>LockModeType.PESSIMISTIC_WRITE</code> can be used when querying data and
 * there is a high likelihood of deadlock or update failure among
 * concurrent updating transactions.
 * 
 * <p> The persistence implementation must support use of locks of type
 * <code>LockModeType.PESSIMISTIC_READ</code> 
 * <code>LockModeType.PESSIMISTIC_WRITE</code> on a non-versioned entity as well as
 * on a versioned entity.
 *
 * <p> When the lock cannot be obtained, and the database locking
 * failure results in transaction-level rollback, the provider must
 * throw the {@link PessimisticLockException} and ensure that the JTA
 * transaction or <code>EntityTransaction</code> has been marked for rollback.
 * 
 * <p> When the lock cannot be obtained, and the database locking
 * failure results in only statement-level rollback, the provider must
 * throw the {@link LockTimeoutException} (and must not mark the transaction
 * for rollback).
 *
 * @since Java Persistence 1.0
 *
 */
public enum LockModeType
{
    /**
     *  <code>OPTIMISTIC</code>と同じ意味。
     * 
     *  新しいアプリケーションでは<code>OPTIMISTIC</code>が好ましい。
     *
     */
    READ,

    /**
     *  <code>OPTIMISTIC_FORCE_INCREMENT</code>と同じ意味。
     * 
     *  新しいアプリケーションでは<code>OPTIMISTIC_FORCE_IMCREMENT</code>が好ましい。
     *
     */
    WRITE,

    /**
     * 楽観ロック。
     *
     * @since Java Persistence 2.0
     */
    OPTIMISTIC,

    /**
     * バージョンのアップデートを伴う楽観ロック。
     *
     * @since Java Persistence 2.0
     */
    OPTIMISTIC_FORCE_INCREMENT,

    /**
     *
     * 悲観読み込みロック。
     *
     * @since Java Persistence 2.0
     */
    PESSIMISTIC_READ,

    /**
     * 悲観書き込みロック。
     *
     * @since Java Persistence 2.0
     */
    PESSIMISTIC_WRITE,

    /**
     * バージョンのアップデートを伴う悲観書き込みロック。
     *
     * @since Java Persistence 2.0
     */
    PESSIMISTIC_FORCE_INCREMENT,

    /**
     * ロックしない。
     *
     * @since Java Persistence 2.0
     */
    NONE
}
