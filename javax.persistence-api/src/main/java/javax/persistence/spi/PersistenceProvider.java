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
     * <code>EntityManagerFactory</code>を作成するときに<code>Persistence</code>クラスによって呼び出されます。
     *
     * @param emName  永続化ユニットの名前
     * @param map  永続化プロバイダによって使用されるプロパティのマップ。このプロパティを使用して<code>persistence.xml</code>ファイル内の対応する要素の値を上書きしたり、
     * <code>persistence.xml</code>で指定されていないプロパティの値を指定することができます。(プロパティを指定しない場合はnullにできます。)
     * @return 永続化ユニットのためのEntityManagerFactory、プロバイダが適切なプロバイダでない場合はnull
     */
    public EntityManagerFactory createEntityManagerFactory(String emName, Map map);

    /**
     * <code>EntityManagerFactory</code>を作成するときにコンテナによって呼び出されます。
     *
     * @param info  永続化プロバイダによって使用されるメタデータ
     * @param map  永続化プロバイダが使用するインテグレーションレベルのプロパティのマップです。(プロパティを指定しない場合はnullにできます。)
     * これらのプロパティにはスキーマの生成を制御するプロパティが含まれる場合があります。
     * Bean Validationプロバイダがクラスパスに存在する場合、コンテナはキーを<code>"javax.persistence.validation.factory"</code>としてマップに含めて
     * <code>ValidatorFactory</code>のインスタンスを渡す必要があります。
     * 格納されているアーカイブがBeanアーカイブの場合、コンテナはBeanManagerインスタンスをキーを<code>"javax.persistence.bean.manager"</code>としてマップに含めて渡す必要があります。
     * @return メタデータによって指定された永続化ユニットのためのEntityManagerFactory
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

