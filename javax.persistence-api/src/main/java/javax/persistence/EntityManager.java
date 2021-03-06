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
package javax.persistence;

import java.util.Map;
import java.util.List;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.CriteriaDelete;

/**
 * 永続化コンテキストと対話するために使用されるインタフェース。
 * 
 * <p> <code>EntityManager</code>のインスタンスは永続化コンテキストに関連付けられています。
 * 永続化コンテキストとは永続的なエンティティの単位に対して一意のエンティティインスタンスが存在するエンティティインスタンスの集合です。
 * 永続化コンテキスト内では、エンティティインスタンスとそのライフサイクルが管理されます。
 * <code>EntityManager</code>のAPIは、永続化されたエンティティインスタンスを作成および削除し、主キーでエンティティを見つけ、エンティティを照会するために使用されます。
 * 
 * <p> 与えられた<code>EntityManager</code>のインスタンスによって管理できるエンティティの集合は永続化ユニットによって定義されます。
 * 永続化ユニットはアプリケーションによって関連付けまたはグループ化され、単一のデータベースへのマッピングで配置されなければならないすべてのクラスの集合を定義します。
 *
 * @see Query
 * @see TypedQuery
 * @see CriteriaQuery
 * @see PersistenceContext
 * @see StoredProcedureQuery
 * 
 * @since Java Persistence 1.0
 */
public interface EntityManager {

    /**
     * インスタンスを管理し、永続化します。
     * @param entity  エンティティのインスタンス
     * @throws EntityExistsException エンティティがすでに存在する場合(エンティティがすでに存在する場合、
     * 永続化操作が実行された場合に<code>EntityExistsException</code>が投げられるか、
     * <code>EntityExistsException</code>もしくはほかの<code>PersistenceException</code>がフラッシュもしくはコミット時に投げられる場合があります。)
     * @throws IllegalArgumentException インスタンスがエンティティでない場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合
     */
    public void persist(Object entity);
    
    /**
     * 与えられたエンティティの状態を現在の永続化コンテキストにマージします。
     * @param entity  エンティティのインスタンス
     * @return 状態がマージされた管理下にあるインスタンス
     * @throws IllegalArgumentException インスタンスがエンティティでないか、削除されたエンティティの場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合
     */    
    public <T> T merge(T entity);

    /**
     * エンティティのインスタンスを削除します。
     * @param entity  エンティティのインスタンス
     * @throws IllegalArgumentException インスタンスがエンティティでないか、デタッチ状態のエンティティの場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合
     */    
    public void remove(Object entity);
    
    /**
     * 主キーで見つけます。
     * 
     * 指定されたクラスと主キーのエンティティを検索します。
     * エンティティインスタンスが永続化コンテキストに含まれている場合はそこから戻されます。
     * @param entityClass  エンティティクラス
     * @param primaryKey  主キー
     * @return 見つかったエンティティのインスタンス、存在しない場合はnull
     * @throws IllegalArgumentException 最初の引数がエンティティ型を示さない場合、または2番目の引数がそのエンティティの主キーの有効な型でないかnullの場合
     */
    public <T> T find(Class<T> entityClass, Object primaryKey);
    
    /**
     * 指定されたプロパティを使用して主キーで見つけます。
     * 
     * 指定されたクラスと主キーのエンティティを検索します。
     * エンティティインスタンスが永続化コンテキストに含まれている場合はそこから戻されます。
     * ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * @param entityClass  エンティティクラス
     * @param primaryKey  主キー
     * @param properties  標準およびベンダー固有のプロパティとヒント
     * @return 見つかったエンティティのインスタンス、存在しない場合はnull
     * @throws IllegalArgumentException 最初の引数がエンティティ型を示さない場合、または2番目の引数がそのエンティティの主キーの有効な型でないかnullの場合
     * @since Java Persistence 2.0
     */ 
    public <T> T find(Class<T> entityClass, Object primaryKey, 
                      Map<String, Object> properties); 
    
    /**
     * 主キーで見つけ、ロックします。
     * 
     * 指定されたクラスと主キーのエンティティを検索し、指定されたロックタイプを配慮してロックします。
     * エンティティインスタンスが永続化コンテキストに含まれ、そこから返される場合、このメソッドはエンティティに対してロックメソッドが呼び出された場合と同じ効果を及ぼします。
     * <p>エンティティが永続化コンテキスト内で見つかり、悲観ロックモードタイプが指定され、エンティティにバージョン属性が含まれている場合、
     * 永続化プロバイダはデータベースロックを取得する際に楽観バージョンチェックも実行する必要があります。
     * これらのチェックに失敗すると<code>OptimisticLockException</code>が投げられます。
     * <p>ロックモードタイプが悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * @param entityClass  エンティティクラス
     * @param primaryKey  主キー
     * @param lockMode  ロックモード
     * @return 見つかったエンティティのインスタンス、存在しない場合はnull
     * @throws IllegalArgumentException 最初の引数がエンティティ型を示さない場合、または2番目の引数がそのエンティティの主キーの有効な型でないかnullの場合
     * @throws TransactionRequiredException トランザクションが存在せず、エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合、
     * または現在のトランザクションに結合されていないエンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合
     * @throws OptimisticLockException 楽観バージョンチェックに失敗した場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     * @since Java Persistence 2.0
     */
    public <T> T find(Class<T> entityClass, Object primaryKey,
                      LockModeType lockMode);

    /**
     * 指定されたプロパティを使用して主キーで見つけ、ロックします。
     * 
     * 指定されたクラスと主キーのエンティティを検索し、指定されたロックタイプを配慮してロックします。
     * エンティティインスタンスが永続化コンテキストに含まれる場合、そこから返されます。
     * <p>エンティティが永続化コンテキストで見つかり、悲観ロックモードタイプが指定され、エンティティにバージョン属性が含まれている場合、
     * 永続化プロバイダはデータベースロックを取得する際に楽観バージョンチェックも実行する必要があります。
     * これらのチェックに失敗すると<code>OptimisticLockException</code>が投げられます。
     * <p>ロックモードタイプが悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * <p>ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * <p>ポータブルアプリケーションでは標準のタイムアウトヒントに頼るべきではありません。
     * 使用されているデータベースとプロバイダが使用しているロックメカニズムによっては、ヒントが観測される場合とされない場合があります。
     * @param entityClass  エンティティクラス
     * @param primaryKey  主キー
     * @param lockMode  ロックモード
     * @param properties  標準およびベンダー固有のプロパティとヒント
     * @return 見つかったエンティティのインスタンス、存在しない場合はnull
     * @throws IllegalArgumentException  最初の引数がエンティティ型を示さない場合、または2番目の引数がそのエンティティの主キーの有効な型でないかnullの場合
     * @throws TransactionRequiredException トランザクションが存在せず、エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合、
     * または現在のトランザクションに結合されていないエンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合
     * @throws OptimisticLockException 楽観バージョンチェックに失敗した場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     * @since Java Persistence 2.0
     */
    public <T> T find(Class<T> entityClass, Object primaryKey,
                      LockModeType lockMode, 
                      Map<String, Object> properties);

    /**
     * 状態がLAZYフェッチされる可能性のあるインスタンスを取得します。
     * 
     * 要求されたインスタンスがデータベースに存在しない場合は、インスタンスの状態に最初にアクセスしたときに<code>EntityNotFoundException</code>がスローされます。
     * (永続化プロバイダーのランタイムは<code>getReference</code>が呼び出されたときに<code>EntityNotFoundException</code>をスローすることが許可されています。)
     * アプリケーションはエンティティマネージャーがオープン状態である間にアプリケーションによってアクセスされた場合を除き
     * デタッチ状態の時にインスタンスの状態が利用可能であることをアプリケーションは期待してはなりません。
     * @param entityClass  エンティティクラス
     * @param primaryKey  主キー
     * @return 見つかったエンティティのインスタンス
     * @throws IllegalArgumentException 最初の引数がエンティティ型を示さない場合、または2番目の引数がそのエンティティの主キーの有効な型でないかnullの場合
     * @throws EntityNotFoundException エンティティの状態にアクセスできない場合
     */
    public <T> T getReference(Class<T> entityClass, 
                                  Object primaryKey);

    /**
     * 永続化コンテキストを基となるデータベースと同期化させます。
     * @throws TransactionRequiredException トランザクションが存在しない場合、もしくはエンティティマネージャーが現在のトランザクションに参加していない場合
     * @throws PersistenceException フラッシュに失敗した場合
     */
    public void flush();

    /**
     * 永続化コンテキストに含まれるすべてのオブジェクトに適用されるフラッシュモードを設定します。
     * @param flushMode  フラッシュモード
     */
    public void setFlushMode(FlushModeType flushMode);

    /**
     * 永続化コンテキストに含まれるすべてのオブジェクトに適用されるフラッシュモードを取得します。
     * @return フラッシュモード
     */
    public FlushModeType getFlushMode();

    /**
     * 永続化コンテキストに含まれるエンティティインスタンスを指定されたロックモード型でロックします。
     * 
     * <p>悲観ロックモードタイプが指定され、エンティティにバージョン属性が含まれている場合、
     * 永続化プロバイダはデータベースロックを取得する際に楽観バージョンチェックも実行する必要があります。
     * これらのチェックに失敗すると<code>OptimisticLockException</code>が投げられます。
     * <p>ロックモードタイプが悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * @param entity  エンティティインスタンス
     * @param lockMode  ロックモード
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはデタッチ状態のエンティティの場合
     * @throws TransactionRequiredException トランザクションが存在しない場合、もしくはエンティティマネージャーが現在のトランザクションに参加していない場合
     * @throws EntityNotFoundException 悲観ロックが行われた時にエンティティがデータベースに存在しない場合
     * @throws OptimisticLockException 楽観バージョンチェックに失敗した場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     */
    public void lock(Object entity, LockModeType lockMode);

    /**
     * 永続化コンテキストに含まれるエンティティインスタンスを指定されたロックモード型と指定されたプロパティでロックします。
     * 
     * <p>悲観ロックモードタイプが指定され、エンティティにバージョン属性が含まれている場合、
     * 永続化プロバイダはデータベースロックを取得する際に楽観バージョンチェックも実行する必要があります。
     * これらのチェックに失敗すると<code>OptimisticLockException</code>が投げられます。
     * <p>ロックモードタイプが悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * <p>ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * <p>ポータブルアプリケーションでは標準のタイムアウトヒントに頼るべきではありません。
     * 使用されているデータベースとプロバイダが使用しているロックメカニズムによっては、ヒントが観測される場合とされない場合があります。
     * @param entity  エンティティインスタンス
     * @param lockMode  ロックモード
     * @param properties  標準およびベンダー固有のプロパティとヒント
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはデタッチ状態のエンティティの場合
     * @throws TransactionRequiredException トランザクションが存在しない場合、もしくはエンティティマネージャーが現在のトランザクションに参加していない場合
     * @throws EntityNotFoundException 悲観ロックが行われた時にエンティティがデータベースに存在しない場合
     * @throws OptimisticLockException 楽観バージョンチェックに失敗した場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     * @since Java Persistence 2.0
     */
    public void lock(Object entity, LockModeType lockMode,
                     Map<String, Object> properties);

    /**
     * データベースからのインスタンスの状態をリフレッシュし、存在する場合はエンティティに加えられた変更を上書きします。
     * @param entity  エンティティインスタンス
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはエンティティが管理下にない場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合
     * @throws EntityNotFoundException エンティティがデータベースに存在しない場合
     */    
    public void refresh(Object entity);

    /**
     * 指定されたプロパティを使用してデータベースからのインスタンスの状態をリフレッシュし、存在する場合はエンティティに加えられた変更を上書きします。
     * 
     * <p> ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * @param entity  エンティティインスタンス
     * @param properties  標準およびベンダー固有のプロパティとヒント
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはエンティティが管理下にない場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合
     * @throws EntityNotFoundException エンティティがデータベースに存在しない場合
     * @since Java Persistence 2.0
     */     
    public void refresh(Object entity,
                            Map<String, Object> properties); 

    /**
     * データベースからのインスタンスの状態をリフレッシュし、存在する場合はエンティティに加えられた変更を上書きし、
     * 指定されたロックモード型でロックします。
     * 
     * <p>ロックモードタイプが悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * @param entity  エンティティインスタンス
     * @param lockMode  ロックモード
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはエンティティが管理下にない場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合、
     * トランザクションが存在せず拡張エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合、
     * または現在のトランザクションに結合されていない拡張エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合
     * @throws EntityNotFoundException エンティティがデータベースに存在しない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     * @since Java Persistence 2.0
     */
    public void refresh(Object entity, LockModeType lockMode);

    /**
     * データベースからのインスタンスの状態をリフレッシュし、存在する場合はエンティティに加えられた変更を上書きし、
     * 指定されたロックモード型および指定されたプロパティでロックします。
     * 
     * <p>ロックモード型が悲観ロックで、エンティティインスタンスが見つかってもロックできない場合は、次のようになります。
     * <ul>
     * <li>データベースのロックが失敗した場合にトランザクションレベルのロールバックが発生すると、<code>PessimisticLockException</code>が投げられます。
     * <li>データベースのロックに失敗した場合にステートメントレベルのロールバックのみが発生すると、<code>LockTimeoutException</code>が投げられます。
     * </ul>
     * <p>ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * <p>ポータブルアプリケーションでは標準のタイムアウトヒントに頼るべきではありません。
     * 使用されているデータベースとプロバイダが使用しているロックメカニズムによっては、ヒントが観測される場合とされない場合があります。
     * @param entity  エンティティインスタンス
     * @param lockMode  ロックモード
     * @param properties  標準およびベンダー固有のプロパティとヒント
     * @throws IllegalArgumentException インスタンスがエンティティでない場合、もしくはエンティティが管理下にない場合
     * @throws TransactionRequiredException トランザクションが存在しないときに<code>PersistenceContextType.TRANSACTION</code>型のコンテナ管理エンティティマネージャーで呼び出された場合、
     * トランザクションが存在せず拡張エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合、
     * または現在のトランザクションに結合されていない拡張エンティティマネージャーで呼び出された時に<code>NONE</code>以外のロックモードが指定されている場合
     * @throws EntityNotFoundException エンティティがデータベースに存在しない場合
     * @throws PessimisticLockException 悲観ロックに失敗し、トランザクションがロールバックされた場合
     * @throws LockTimeoutException 悲観ロックに失敗し、ステートメントのみがロールバックされた場合
     * @throws PersistenceException サポートされていないロック呼び出しが行われた場合
     * @since Java Persistence 2.0
     */
    public void refresh(Object entity, LockModeType lockMode,
                        Map<String, Object> properties);
    
    /**
     * 永続化コンテキストをクリアし、すべての管理下のエンティティをデタッチ状態にします。
     * 
     * データベースにフラッシュされていないエンティティへの変更はデータベースに永続化されません。
     */
    public void clear();

    /**
     * 与えられたエンティティを永続化コンテキストから削除し、マネージドエンティティをデタッチ状態にします。
     * 
     * フラッシュされてないエンティティへの変更(エンティティの削除を含む)があった場合、それらはデータベースへ同期化されません。
     * 以前にデタッチ状態になったエンティティを参照するエンティティは、それを引き続き参照します。
     * @param entity  エンティティのインスタンス
     * @throws IllegalArgumentException インスタンスがエンティティでない場合
     * @since Java Persistence 2.0
     */
    public void detach(Object entity); 

    /**
     * インスタンスが現在の永続化コンテキストに所属する管理下のエンティティインスタンスであるかを確認します。
     * @param entity  エンティティのインスタンス
     * @return 永続化コンテキスト内のエンティティであるかを示すboolean
     * @throws IllegalArgumentException エンティティでない場合
     */    
    public boolean contains(Object entity);

    /**
     * エンティティインスタンスのための現在のロックモードを取得します。
     * @param entity  エンティティインスタンス
     * @return ロックモード
     * @throws TransactionRequiredException トランザクションが存在しない場合、
     * もしくはエンティティマネージャーが現在のトランザクションに参加していない場合
     * @throws IllegalArgumentException インスタンスが管理下のエンティティでなく、トランザクションがアクティブな場合
     * @since Java Persistence 2.0
     */
    public LockModeType getLockMode(Object entity);

    /** 
     * エンティティマネージャーにプロパティもしくはヒントを設定します。
     * 
     * ベンダー固有のプロパティまたはヒントが認識されない場合、それは暗黙のうちに無視されます。
     * @param propertyName プロパティもしくはヒントの名前
     * @param value  プロパティーもしくはヒントの値
     * @throws IllegalArgumentException 2つ目の引数が実装に対して不正な場合
     * @since Java Persistence 2.0
     */ 
    public void setProperty(String propertyName, Object value);

    /**
     * エンティティマネージャーに有効なプロパティーとヒントと関連する値を取得します。
     * 
     * Mapの内容への変更は有効な設定に影響を与えません。
     * @return エンティティマネージャーに有効なプロパティのマップとヒント
     * @since Java Persistence 2.0
     */
    public Map<String, Object> getProperties();

    /**
     * JPQLのステートメントを実行するための<code>Query</code>のインスタンスを作成します。
     * @param qlString JQPLの文字列
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException クエリー文字列が不正である場合
     */
    public Query createQuery(String qlString);

    /**
     * クライテリアクエリーのための<code>TypedQuery</code>のインスタンスを作成します。
     * @param criteriaQuery  クライテリアクエリーオブジェクト
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException クライテリアクエリー文字列が不正である場合
     * @since Java Persistence 2.0
     */
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery); 

    /**
     * クライテリア更新クエリーのための<code>Query</code>のインスタンスを作成します。
     * @param updateQuery  クライテリア更新クエリーオブジェクト
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException 更新クエリー文字列が不正である場合
     * @since Java Persistence 2.1
     */
    public Query createQuery(CriteriaUpdate updateQuery);

    /**
     * クライテリア削除クエリーのための<code>Query</code>のインスタンスを作成します。
     * @param deleteQuery  クライテリア削除クエリーオブジェクト
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException 削除クエリー文字列が不正である場合
     * @since Java Persistence 2.1
     */
    public Query createQuery(CriteriaDelete deleteQuery);

    /**
     * JPQLのステートメントを実行するための<code>TypedQuery</code>のインスタンスを作成します。
     * 
     * クエリーのSELECTリストは一つの項目のみ含まれなければならず、<code>resultClass</code>引数で指定された型に割り当て可能である必要があります。
     * @param qlString JQPLの文字列
     * @param resultClass クエリーの結果の型
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException クエリー文字列が不正である場合、
     * またはクエリー結果が指定された型に割り当てる事が出来ない場合
     * @since Java Persistence 2.0
     */
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass);

    /**
     * 名前付き(JPQLまたはネイティブSQLの)クエリーを実行するための<code>Query</code>インスタンスを作成します。
     * @param name メタデータ内で定義されたクエリーの名前
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException 指定された名前でクエリーが定義されていない場合、またはクエリー文字列が不正である場合
     */
    public Query createNamedQuery(String name);

    /**
     * JPQLの名前付きクエリーを実行するための<code>TypedQuery</code>のインスタンスを作成します。
     * 
     * クエリーのSELECTリストは一つの項目のみ含まれなければならず、<code>resultClass</code>引数で指定された型に割り当て可能である必要があります。
     * @param name メタデータ内で定義されたクエリーの名前
     * @param resultClass クエリーの結果の型
     * @return 新しいクエリーのインスタンス
     * @throws IllegalArgumentException 指定された名前でクエリーが定義されていない場合、またはクエリー文字列が不正である場合、
     * またはクエリー結果が指定された型に割り当てる事が出来ない場合
     * @since Java Persistence 2.0
     */
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass);

    /**
     * ネイティブSQLクエリーを実行するための<code>Query</code>のインスタンスを作成します。例えば、更新または削除です。
     * 
     * クエリーが更新もしくは削除用のクエリーでない場合、クエリーの実行により、SQLの結果の各行はObject[]型の結果(またはSELECTリストにカラムが1つのみの場合はObject型の結果)
     * として戻されます。
     * カラムの値はSELECTリストに表示される順に戻され、デフォルトのJDBC型のマッピングが適用されます。
     * @param sqlString ネイティブSQLクエリー文字列
     * @return 新しいクエリーのインスタンス
     */
    public Query createNativeQuery(String sqlString);

    /**
     * ネイティブSQLクエリーを実行するための<code>Query</code>のインスタンスを作成します。
     * @param sqlString ネイティブSQLクエリー文字列
     * @param resultClass 結果のインスタンスのクラス
     * @return 新しいクエリーのインスタンス
     */
    public Query createNativeQuery(String sqlString, Class resultClass);

    /**
     * ネイティブSQLクエリーを実行するための<code>Query</code>のインスタンスを作成します。
     * @param sqlString ネイティブSQLクエリー文字列
     * @param resultSetMapping 結果セットのマッピングの名前
     * @return 新しいクエリーのインスタンス
     */
    public Query createNativeQuery(String sqlString, String resultSetMapping);

    /**
     * ストアドプロシージャーをデータベースで実行するための<code>StoredProcedureQuery</code>のインスタンスを作成します。
     * 
     * <p>パラメーターはストアドプロシージャーが実行される前に登録される必要があります。
     * <p>ストアドプロシージャーが一つ以上の結果を返す場合、結果セットはObject[]型のリストとして戻されます。
     * @param name メタデータ内のストアドプロシージャークエリーに割り当てられている名前
     * @return 新しいストアドプロシージャークエリーのインスタンス
     * @throws IllegalArgumentException 与えられた名前のストアドプロシージャーが定義されていない場合
     * @since Java Persistence 2.1
     */
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name);

    /**
     * ストアドプロシージャーをデータベースで実行するための<code>StoredProcedureQuery</code>のインスタンスを作成します。
     * 
     * <p>パラメーターはストアドプロシージャーが実行される前に登録される必要があります。
     * <p>ストアドプロシージャーが一つ以上の結果を返す場合、結果セットはObject[]型のリストとして戻されます。
     * @param procedureName データベース内のストアドプロシージャーの名前
     * @return 新しいストアドプロシージャークエリーのインスタンス
     * @throws IllegalArgumentException 与えられた名前のストアドプロシージャーが存在しない場合(もしくはクエリーの実行が失敗する場合)
     * @since Java Persistence 2.1
     */
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName);

    /**
     * ストアドプロシージャーをデータベースで実行するための<code>StoredProcedureQuery</code>のインスタンスを作成します。
     * 
     * <p>パラメーターはストアドプロシージャーが実行される前に登録される必要があります。
     * <p><code>resultClass</code>引数はストアドプロシージャーの実行で戻される結果セットの順番で指定しなければなりません。
     * @param procedureName データベース内のストアドプロシージャーの名前
     * @param resultClasses ストアドプロシージャーによって生成された結果セットがマッピングされるクラス
     * @return 新しいストアドプロシージャークエリーのインスタンス
     * @throws IllegalArgumentException 与えられた名前のストアドプロシージャーが存在しない場合(もしくはクエリーの実行が失敗する場合)
     * @since Java Persistence 2.1
     */
    public StoredProcedureQuery createStoredProcedureQuery(
	       String procedureName, Class... resultClasses);

    /**
     * ストアドプロシージャーをデータベースで実行するための<code>StoredProcedureQuery</code>のインスタンスを作成します。
     * 
     * <p>パラメーターはストアドプロシージャーが実行される前に登録される必要があります。
     * <p><code>resultSetMapping</code>引数はストアドプロシージャーの実行で戻される結果セットの順番で指定しなければなりません。
     * @param procedureName データベース内のストアドプロシージャーの名前
     * @param resultSetMappings ストアドプロシージャーが返す結果セットのマッピングに使用される結果セットマッピングの名前
     * @return 新しいストアドプロシージャークエリーのインスタンス
     * @throws IllegalArgumentException 与えられた名前のストアドプロシージャーが存在しない場合(もしくはクエリーの実行が失敗する場合)
     */
    public StoredProcedureQuery createStoredProcedureQuery(
              String procedureName, String... resultSetMappings);

    /**
     * エンティティマネージャーにJTAトランザクションがアクティブであることを示し、永続化コンテキストをそれに参加させます。
     * 
     * <p>これはJTAのアプリケーション管理のエンティティマネージャーがアクティブなトランザクションのスコープ外で作られたか、
     * <code>SynchronizationType.UNSYNCHRONIZED</code>型のエンティティマネージャーであるときに、
     * 現在のJTAトランザクションに参加するときに呼び出される必要があります。
     * @throws TransactionRequiredException トランザクションが存在しない場合
     */
    public void joinTransaction();

    /**
     * 現在のトランザクションにエンティティマネージャーが参加しているかどうかを返します。
     * 
     * エンティティマネージャーが現在のトランザクションに参加していない場合やアクティブなトランザクションが存在しない場合はfalseを返します。
     * @return boolean
     * @since Java Persistence 2.1
     */
    public boolean isJoinedToTransaction();

    /**
     * プロバイダ固有のAPIへのアクセスを許可するために指定された型のオブジェクトを返します。
     * 
     * プロバイダの<code>EntityManager</code>の実装が指定されたクラスをサポートしていない場合はPersistenceExceptionが投げられます。
     * @param cls  返されるオブジェクトのクラス、これは通常は<code>EntityManager</code>の基となる実装クラスか、その実装のインターフェース
     * @return 指定されたクラスのインスタンス
     * @throws PersistenceException プロバイダがこの呼び出しをサポートしていない場合
     * @since Java Persistence 2.0
     */
    public <T> T unwrap(Class<T> cls); 

    /**
     * 利用可能な場合に<code>EntityManager</code>の基となるプロバイダーオブジェクトを返します。
     * 
     * このメソッドの結果は実装に依存します。
     * 
     * 新しいアプリケーションでは<code>unwrap</code>メソッドを使用するのが望ましいです。
     * @return EntityManagerの基となるプロバイダーオブジェクト
     */
    public Object getDelegate();

    /**
     * アプリケーション管理のエンティティマネージャーをクローズします。
     * このメソッドが呼び出された後、この<code>EntityManager</code>と、
     * この<code>EntityManager</code>から得られたすべての<code>Query</code>と<code>TypedQuery</code>、
     * <code>StoredProcedureQuery</code>オブジェクトのすべてのメソッドは<code>getProperties</code>と、<code>getTransaction</code>、
     * <code>isOpen</code>(このメソッドはfalseを返します)を除いて<code>IllegalStateException</code>を投げるようになります。
     * エンティティマネージャーがアクティブなトランザクションに参加しているときにこのメソッドが呼び出された場合、
     * トランザクションが完了するまで永続化コンテキストは管理されたままとなります。
     * @throws IllegalStateException エンティティマネージャーがコンテナ管理の場合
     */
    public void close();

    /**
     * エンティティマネージャーがオープンしているかどうかを返します。
     * @return エンティティマネージャーがクローズされるまではtrue
     */
    public boolean isOpen();

    /**
     * リソースレベルの<code>EntityTransaction</code>オブジェクトを返します。
     * 
     * <code>EntityTransaction</code>インスタンスは複数のトランザクションを開始およびコミットするために連続して使用できます。
     * @return EntityTransaction instance
     * @throws IllegalStateException JTAのエンティティマネージャーで実行された場合
     */
    public EntityTransaction getTransaction();

    /**
     * このエンティティマネージャーのエンティティマネージャーファクトリーを返します。
     * @return EntityManagerFactoryのインスタンス
     * @throws IllegalStateException エンティティマネージャーがすでにクローズされている場合
     * @since Java Persistence 2.0
     */
    public EntityManagerFactory getEntityManagerFactory();

    /**
     * <code>CriteriaQuery</code>オブジェクトを作るための<code>CriteriaBuilder</code>のインスタンスを返します。
     * @return CriteriaBuilderのインスタンス
     * @throws IllegalStateException エンティティマネージャーがすでにクローズされている場合
     * @since Java Persistence 2.0
     */
    public CriteriaBuilder getCriteriaBuilder();

    /**
     * 永続化ユニットのメタモデルにアクセスするための<code>Metamodel</code>インターフェースのインスタンスを返します。
     * @return Metamodelのインスタンス
     * @throws IllegalStateException エンティティマネージャーがすでにクローズされている場合
     * @since Java Persistence 2.0
     */
    public Metamodel getMetamodel();

    /**
     * 動的にEntityGraphを作成するのに使用できる可変なEntityGraphを返します。
     * @param rootType エンティティグラフのクラス
     * @return エンティティグラフ
     * @since Java Persistence 2.1
     */
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType);

    /**
     * 名前付きEntityGraphの可変なコピーを返します。
     * 
     * 指定された名前でエンティティグラフが存在しない場合はnullを返します。
     * @param graphName エンティティグラフの名前
     * @return エンティティグラフ
     * @since Java Persistence 2.1
     */
    public EntityGraph<?> createEntityGraph(String graphName);

    /**
     * 名前付きEntityGraphを返します。
     * 
     * 返されたEntityGraphは不変であるとみなされる必要があります。
     * @param graphName  存在するエンティティグラフの名前
     * @return 名前付きエンティティグラフ
     * @throws IllegalArgumentException 与えられた名前のEntityGraphが存在しない場合
     * @since Java Persistence 2.1
     */
    public  EntityGraph<?> getEntityGraph(String graphName);

    /**
     * 与えられたクラスの型のために定義されたすべての名前付きEntityGraphを返します。
     * @param entityClass  エンティティクラス
     * @return 指定されたエンティティのために定義されたすべてのエンティティグラフのリスト
     * @throws IllegalArgumentException クラスがエンティティではない場合
     * @since Java Persistence 2.1
     */
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass);

}
