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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import javax.persistence.spi.LoadState;

/**
 * Java SE環境で{@link EntityManagerFactory}を得るために使用されるブートストラップクラスです。
 * 
 * また、スキーマの生成を行わせるために使用することもできます。
 * 
 * <p> <code>Persistence</code>クラスはJava EEコンテナ環境でも使用できます。
 * しかしながらコンテナ環境ではJava SEブートストラップAPIのサポートは必須ではありません。
 * 
 * <p> <code>Persistence</code>クラスはJava EE環境とJava SE環境の両方で
 * {@link javax.persistence.PersistenceUtil PersistenceUtil}のインスタンスを取得するために使用されます。
 *
 * @since Java Persistence 1.0
 */
public class Persistence {
    
    /**
     * 指定された名前の永続化ユニットのためのEntityManagerFactoryを作成し、返します。
     * 
     * @param persistenceUnitName 永続化ユニットの名前
     * @return 指定された永続化ユニットに従って構成されたEntityManagerを作成するファクトリー
     */
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        return createEntityManagerFactory(persistenceUnitName, null);
    }

    /**
     * 与えられたプロパティを使用した指定された名前の永続化ユニットのためのEntityManagerFactoryを作成し、返します。
     * 
     * @param persistenceUnitName 永続化ユニットの名前
     * @param properties ファクトリーを作成する時に使用される追加のプロパティ。
     *            このプロパティはスキーマ生成のコントロールのためのプロパティを含めることができます。
     *            このプロパティの値は他の場所で設定されている値を上書きします。
     * @return 指定された永続化ユニットに従って設定されたEntityManagerを作成するファクトリー
     */
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map properties) {

        EntityManagerFactory emf = null;
        PersistenceProviderResolver resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();

        List<PersistenceProvider> providers = resolver.getPersistenceProviders();

        for (PersistenceProvider provider : providers) {
            emf = provider.createEntityManagerFactory(persistenceUnitName, properties);
            if (emf != null) {
                break;
            }
        }
        if (emf == null) {
            throw new PersistenceException("No Persistence provider for EntityManager named " + persistenceUnitName);
        }
        return emf;
    }


    /**
     * 指定されたプロパティから決定されるデータベーススキーマと/もしくはテーブルを作成するか/もしくはDDLスクリプトを作成します。
     * 
     * <p>スキーマ生成がエンティティマネージャファクトリの作成とは別のフェーズとして実行されるときに呼び出されます。
     * @param persistenceUnitName 永続化ユニットの名前
     * @param map スキーマ生成のプロパティ、それらはプロバイダ固有のプロパティを含めることができます。
     *            このプロパティの値は他の場所で設定されている値を上書きします。
     * @throws PersistenceException 提供された構成情報が不十分か矛盾があった場合、またはスキーマ生成が失敗した場合
     *
     * @since Java Persistence 2.1
     */
    public static void generateSchema(String persistenceUnitName, Map map) {
        PersistenceProviderResolver resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();
        List<PersistenceProvider> providers = resolver.getPersistenceProviders();
        
        for (PersistenceProvider provider : providers) {
            if (provider.generateSchema(persistenceUnitName, map)) {
                return;
            }
        }
        
        throw new PersistenceException("No Persistence provider to generate schema named " + persistenceUnitName);
    }
    

    /**
     * PersistenceUtilのインスタンスを返します。
     * @return PersistenceUtilのインスタンス
     * @since Java Persistence 2.0
     */
    public static PersistenceUtil getPersistenceUtil() {
       return new PersistenceUtilImpl();
    }

    
    /**
     * PersistenceUtilインターフェースの実装です。
     * @since Java Persistence 2.0
     */
    private static class PersistenceUtilImpl implements PersistenceUtil {
        public boolean isLoaded(Object entity, String attributeName) {
            PersistenceProviderResolver resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();

            List<PersistenceProvider> providers = resolver.getPersistenceProviders();

            for (PersistenceProvider provider : providers) {
                LoadState loadstate = provider.getProviderUtil().isLoadedWithoutReference(entity, attributeName);
                if(loadstate == LoadState.LOADED) {
                    return true;
                } else if (loadstate == LoadState.NOT_LOADED) {
                    return false;
                } // else continue
            }

            //None of the providers could determine the load state try isLoadedWithReference
            for (PersistenceProvider provider : providers) {
                LoadState loadstate = provider.getProviderUtil().isLoadedWithReference(entity, attributeName);
                if(loadstate == LoadState.LOADED) {
                    return true;
                } else if (loadstate == LoadState.NOT_LOADED) {
                    return false;
                } // else continue
            }

            //None of the providers could determine the load state.
            return true;
        }

        public boolean isLoaded(Object entity) {
            PersistenceProviderResolver resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();

            List<PersistenceProvider> providers = resolver.getPersistenceProviders();

            for (PersistenceProvider provider : providers) {
                LoadState loadstate = provider.getProviderUtil().isLoaded(entity);
                if(loadstate == LoadState.LOADED) {
                    return true;
                } else if (loadstate == LoadState.NOT_LOADED) {
                    return false;
                } // else continue
            }
            //None of the providers could determine the load state
            return true;
        }
    }

    /**
     * このfinalな文字列は非推奨で、削除されるべきです。これはTCK下位互換のためにのみ存在します。
     * @since Java Persistence 1.0
     * @deprecated
     */
    @Deprecated
    public static final String PERSISTENCE_PROVIDER = "javax.persistence.spi.PeristenceProvider";
    
    /**
     * このインスタンス変数は非推奨で、削除されるべきです。これはTCK下位互換のためにのみ存在します。
     * @since Java Persistence 1.0
     * @deprecated
     */
    @Deprecated
    protected static final Set<PersistenceProvider> providers = new HashSet<PersistenceProvider>();
}
