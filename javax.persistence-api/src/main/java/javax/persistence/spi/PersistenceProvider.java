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
package javax.persistence.spi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.Map;

/**
 * 永続化プロバイダによって実装されるインタフェースです。
 * 
 * <p> これは、Java EE環境のコンテナと、Java SE環境の{@link javax.persistence.Persistence}クラスによって呼び出され、
 * {@link javax.persistence.EntityManagerFactory}を作成したり、スキーマの生成を行います。
 *
 * @since Java Persistence 1.0
 */
public interface PersistenceProvider {

    /**
     * Called by <code>Persistence</code> class when an
     * <code>EntityManagerFactory</code> is to be created.
     *
     * @param emName  the name of the persistence unit
     * @param map  a Map of properties for use by the 
     * persistence provider. These properties may be used to
     * override the values of the corresponding elements in 
     * the <code>persistence.xml</code> file or specify values for 
     * properties not specified in the <code>persistence.xml</code>
     * (and may be null if no properties are specified).
     * @return EntityManagerFactory for the persistence unit, 
     * or null if the provider is not the right provider 
     */
    public EntityManagerFactory createEntityManagerFactory(String emName, Map map);

    /**
     * Called by the container when an <code>EntityManagerFactory</code>
     * is to be created. 
     *
     * @param info  metadata for use by the persistence provider
     * @param map  a Map of integration-level properties for use 
     * by the persistence provider (may be null if no properties
     * are specified).  These properties may include properties to
     * control schema generation.
     * If a Bean Validation provider is present in the classpath,
     * the container must pass the <code>ValidatorFactory</code> instance in
     * the map with the key <code>"javax.persistence.validation.factory"</code>.
     * If the containing archive is a bean archive, the container
     * must pass the BeanManager instance in the map with the key
     * <code>"javax.persistence.bean.manager"</code>.
     * @return EntityManagerFactory for the persistence unit 
     * specified by the metadata
     */
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map);


    /**
     * データベーススキーマやテーブルを作成し、提供されたプロパティによって決定されるDDLスクリプトを作成します。
     * 
     * <p>
     * スキーマ生成がエンティティマネージャーファクトリーの作成とは別のフェーズとして実行されるときにコンテナによって呼び出されます。
     * <p>
     * @param info 永続化プロバイダによって使用されるメタデータ
     * @param map スキーマ生成のためのプロパティ、これらにはプロバイダ固有のプロパティも含まれる。
     * @throws PersistenceException 構成情報が不十分または不正確な場合、またはスキーマ生成が失敗した場合
     *
     * @since Java Persistence 2.1
     */
    public void generateSchema(PersistenceUnitInfo info, Map map);

    /**
     * データベーススキーマやテーブルを作成し、提供されたプロパティによって決定されるDDLスクリプトを作成します。
     * 
     * <p>
     * スキーマ生成がエンティティマネージャーファクトリーの作成とは別のフェーズとして実行されるときにPersistenceクラスによって呼び出されます。
     * <p>
     * @param persistenceUnitName 永続化ユニットの名前
     * @param map スキーマ生成のためのプロパティ、これらにはプロバイダ固有のプロパティも含まれる。このプロパティの値は他の場所で設定されている値を上書きします。
     * @return スキーマが生成された場合はtrue、そうでない場合はfalse
     * @throws PersistenceException 構成情報が不十分または不正確な場合、またはスキーマ生成が失敗した場合
     *
     * @since Java Persistence 2.1
     */
    public boolean generateSchema(String persistenceUnitName, Map map); 

    /**
     * 永続化プロバイダによって実装されたユーティリティインタフェースを返します。
     * @return ProviderUtilインターフェース
     *
     * @since Java Persistence 2.0
     */
    public ProviderUtil getProviderUtil();
}

