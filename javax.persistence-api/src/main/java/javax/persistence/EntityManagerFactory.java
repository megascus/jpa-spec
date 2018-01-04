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
import javax.persistence.metamodel.Metamodel;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Interface used to interact with the entity manager factory
 * for the persistence unit.
 *
 * <p>When the application has finished using the entity manager
 * factory, and/or at application shutdown, the application should
 * close the entity manager factory.  Once an
 * <code>EntityManagerFactory</code> has been closed, all its entity managers
 * are considered to be in the closed state.
 *
 * @since Java Persistence 1.0
 */
public interface EntityManagerFactory {

    /**
     * Create a new application-managed <code>EntityManager</code>.
     * This method returns a new <code>EntityManager</code> instance each time
     * it is invoked. 
     * The <code>isOpen</code> method will return true on the returned instance.
     * @return エンティティマネージャーのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     */
    public EntityManager createEntityManager();
    
    /**
     * Create a new application-managed <code>EntityManager</code> with the 
     * specified Map of properties. 
     * This method returns a new <code>EntityManager</code> instance each time
     * it is invoked. 
     * The <code>isOpen</code> method will return true on the returned instance.
     * @param map エンティティマネージャーのためのプロパティ
     * @return エンティティマネージャーのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     */
    public EntityManager createEntityManager(Map map);

    /**
     * Create a new JTA application-managed <code>EntityManager</code> with the 
     * specified synchronization type.
     * This method returns a new <code>EntityManager</code> instance each time
     * it is invoked. 
     * The <code>isOpen</code> method will return true on the returned instance.
     * @param synchronizationType  エンティティマネージャーが何時どのように現在のJTAトランザクションと同期をとる必要があるか
     * @return エンティティマネージャーのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがリソースローカルなエンティティマネージャーとして構成されている場合か、
     * すでにクローズされている場合
     *
     * @since Java Persistence 2.1
     */
    public EntityManager createEntityManager(SynchronizationType synchronizationType);

    /**
     * Create a new JTA application-managed <code>EntityManager</code> with the 
     * specified synchronization type and map of properties. 
     * This method returns a new <code>EntityManager</code> instance each time
     * it is invoked. 
     * The <code>isOpen</code> method will return true on the returned instance.
     * @param synchronizationType  エンティティマネージャーが何時どのように現在のJTAトランザクションと同期をとる必要があるか
     * @param map エンティティマネージャーのためのプロパティ
     * @return エンティティマネージャーのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがリソースローカルなエンティティマネージャーとして構成されている場合か、
     * すでにクローズされている場合
     *
     * @since Java Persistence 2.1
     */
    public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map);

    /**
     * <code>CriteriaQuery</code>オブジェクトを作るための<code>CriteriaBuilder</code>のインスタンスを返します。
     * @return CriteriaBuilderのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     *
     * @since Java Persistence 2.0
     */
    public CriteriaBuilder getCriteriaBuilder();
    
    /**
     * 永続化ユニットのメタモデルにアクセスするための<code>Metamodel</code>インターフェースのインスタンスを返します。
     * @return Metamodelのインスタンス
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     *
     * @since Java Persistence 2.0
     */
    public Metamodel getMetamodel();

    /**
     * ファクトリーがオープンしているかどうかを示します。
     * 
     * ファクトリーがクローズされるまではtrueを返します。
     * @return ファクトリーがオープンしているかどうかを示すboolean
     */
    public boolean isOpen();
    
    /**
     * Close the factory, releasing any resources that it holds.
     * After a factory instance has been closed, all methods invoked
     * on it will throw the <code>IllegalStateException</code>, except
     * for <code>isOpen</code>, which will return false. Once an
     * <code>EntityManagerFactory</code> has been closed, all its
     * entity managers are considered to be in the closed state.
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     */
    public void close();

    /**
     * Get the properties and associated values that are in effect
     * for the entity manager factory. Changing the contents of the
     * map does not change the configuration in effect.
     * @return プロパティ
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     *
     * @since Java Persistence 2.0
     */
    public Map<String, Object> getProperties();

    /**
     * Access the cache that is associated with the entity manager 
     * factory (the "second level cache").
     * @return <code>Cache</code>インターフェースのインスタンス、キャッシュが使用されていない場合はnull
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     *
     * @since Java Persistence 2.0
     */
    public Cache getCache();

    /**
     * 永続化ユニットのためのユーティリティーメソッドへのアクセスを提供するインターフェースを返します。
     * @return <code>PersistenceUnitUtil</code>インターフェース
     * @throws IllegalStateException このエンティティマネージャーファクトリーがすでにクローズされている場合
     *
     * @since Java Persistence 2.0
     */
    public PersistenceUnitUtil getPersistenceUnitUtil();

    /**
     * Define the query, typed query, or stored procedure query as
     * a named query such that future query objects can be created
     * from it using the <code>createNamedQuery</code> or
     * <code>createNamedStoredProcedureQuery</code> method.
     * <p>Any configuration of the query object (except for actual
     * parameter binding) in effect when the named query is added
     * is retained as part of the named query definition.
     * This includes configuration information such as max results,
     * hints, flush mode, lock mode, result set mapping information,
     * and information about stored procedure parameters.
     * <p>When the query is executed, information that can be set
     * by means of the query APIs can be overridden. Information
     * that is overridden does not affect the named query as
     * registered with the entity manager factory, and thus does
     * not affect subsequent query objects created from it by
     * means of the <code>createNamedQuery</code> or
     * <code>createNamedStoredProcedureQuery</code> method.
     * <p>If a named query of the same name has been previously
     * defined, either statically via metadata or via this method,
     * that query definition is replaced.
     *
     * @param name クエリーのための名前
     * @param query QueryもしくはTypedQuery、StoredProcedureQueryのオブジェクト
     *
     * @since Java Persistence 2.1
     */
    public void addNamedQuery(String name, Query query);

    /**
     * プロバイダ固有のAPIへのアクセスを許可するために指定された型のオブジェクトを返します。
     * 
     * プロバイダのEntityManagerFactoryの実装が指定されたクラスをサポートしていない場合はPersistenceExceptionが投げられます。
     * @param cls 返されるオブジェクトのクラス、これは通常はEntityManagerFactoryの基となる実装クラスか、その実装のインターフェース
     * @return 指定されたクラスのインスタンス
     * @throws PersistenceException プロバイダがこの呼び出しをサポートしていない場合
     * @since Java Persistence 2.1
     */
    public <T> T unwrap(Class<T> cls);

    /**
     * EntityManagerFactoryにエンティティグラフの名前の付いたコピーを追加します。
     * 
     * すでに同じ名前のエンティティグラフが存在する場合、置き換えられます。
     * @param graphName  エンティティグラフの名前
     * @param entityGraph  エンティティグラフ
     * @since Java Persistence 2.1
     */
    public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph);

}
