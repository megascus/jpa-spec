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

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;
import java.net.URL;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;

/**
 * コンテナによって実装され、{@link javax.persistence.EntityManagerFactory}作成時に永続化プロバイダによって使用されるインタフェースです。
 *
 * @since Java Persistence 1.0
 */
public interface PersistenceUnitInfo {
	
    /**
     * 永続化ユニットの名前を返します。
     * 
     * <code>persistence.xml</code>ファイルの<code>name</code>属性に対応します。
     * @return  永続化ユニットの名前
     */
    public String getPersistenceUnitName();

    /**
     * 永続化プロバイダの実装クラスの完全修飾名(FQCN)を返します。
     * 
     * <code>persistence.xml</code>ファイルの<code>provider</code>要素に対応します。
     * @return  永続化プロバイダの実装クラスの完全修飾名(FQCN)
     */
    public String getPersistenceProviderClassName();

    /**
     * <code>EntityManagerFactory</code>によって作成されたエンティティマネージャーのトランザクションタイプを返します。
     * 
     * トランザクションタイプは<code>persistence.xml</code>ファイルの<code>transaction-type</code>属性に対応します。
     * @return  EntityManagerFactoryによって作成されたエンティティマネージャーのトランザクションタイプ
     */
    public PersistenceUnitTransactionType getTransactionType();

    /**
     * 永続化プロバイダによって使用されるJTA対応のデータソースを返します。
     * 
     * データソースは<code>persistence.xml</code>ファイルの<code>jta-data-source</code>要素もしくはデプロイ時やコンテナにより提供されるものに対応します。
     * @return 永続化プロバイダによって使用されるJTA対応のデータソース
     */
    public DataSource getJtaDataSource();

    /**
     * 永続化プロバイダがJTAトランザクションの外のデータにアクセスするために使用する非JTA対応のデータソースを返します。
     * 
     * データソースは<code>persistence.xml</code>ファイルの<code>non-jta-data-source</code>要素もしくはデプロイ時やコンテナにより提供されるものに対応します。
     * @return 永続化プロバイダがJTAトランザクションの外のデータにアクセスするために使用する非JTA対応のデータソース
     */
    public DataSource getNonJtaDataSource();

    /**
     * エンティティクラスのマッピングを決定するために永続化プロバイダがロードする必要があるマッピングファイルの名前のリストを返します。
     * 
     * マッピングファイルは標準のXMLマッピング形式であり、アプリケーションクラスパスで一意の名前を持ち、リソースとして読み込み可能でなければなりません。
     * 各マッピングファイル名は<code>persistence.xml</code>ファイルの<code>mapping-file</code>要素に対応します。
     * @return エンティティクラスのマッピングを決定するために永続化プロバイダがロードする必要があるマッピングファイルの名前のリスト
     */
    public List<String> getMappingFileNames();

    /**
     * 永続化プロバイダが永続化ユニットの管理対象クラスを調べる必要があるjarファイルまたは展開されたjarファイルディレクトリのURLのリストを返します。
     * 
     * 各URLは<code>persistence.xml</code>ファイルの<code>jar-file</code>要素に対応しています。
     * URLは、jarファイルを参照するURLまたは展開されたjarファイルを含むディレクトリ、またはjar形式のInputStreamを取得するためのその他のURLを参照するURLです。
     * @return jarファイルやディレクトリを参照するURLオブジェクトのリスト
     */
    public List<URL> getJarFileUrls();

    /**
     * 永続化ユニットのルートであるjarファイルまたはディレクトリのURLを返します。
     * 
     * (永続化ユニットがWEB-INF/classesディレクトリにルートされている場合、これはそのディレクトリのURLになります。)
     * URLは、jarファイルを参照するURLまたは展開されたjarファイルを含むディレクトリ、またはjar形式のInputStreamを取得するためのその他のURLを参照するURLです。
     * @return jarファイルやディレクトリを参照するURL
     */
    public URL getPersistenceUnitRootUrl();

    /**
     * 永続化プロバイダが管理対象クラスのセットに追加する必要があるクラスの名称のリストを返します。
     * 
     * 各名称は<code>persistence.xml</code>の名前付き<code>class</code>要素に対応しています。
     * @return 永続化プロバイダが管理対象クラスのセットに追加する必要があるクラスの名前のリスト
     */
    public List<String> getManagedClassNames();

    /**
     * 永続化ユニットのルートに存在するが明示的にリスト化されていないクラスを管理対象クラスのセットに含めるかどうかを返します。
     * @return 永続化ユニットのルートに存在するが明示的にリスト化されていないクラスを管理対象クラスのセットに含めるかどうか
     */
    public boolean excludeUnlistedClasses();

    /**
     * プロバイダが永続化ユニットでL2キャッシュを使用する必要がある方法の仕様を返します。
     * 
     * このメソッドの結果は<code>persistence.xml</code>の<code>shared-cache-mode</code>要素に対応します。
     * @return プロバイダが永続化ユニットにL2キャッシュを使用する必要がある方法の仕様
     *
     * @since Java Persistence 2.0
     */
    public SharedCacheMode getSharedCacheMode();

    /**
     * 永続化ユニットで永続化プロバイダが使用する検証モードを返します。
     * 
     * 検証モードは<code>persistence.xml</code>の<code>validation-mode</code>要素に対応します。
     * @return 永続化ユニットで永続化プロバイダが使用する検証モード
     * 
     * @since Java Persistence 2.0
     */
    public ValidationMode getValidationMode();

    /**
     * プロパティオブジェクトを返します。
     * 
     * 各プロパティは<code>persistence.xml</code>の<code>property</code>要素やコンテナによって設定されたプロパティに対応します。
     * @return プロパティオブジェクト
     */
    public Properties getProperties();
    
    /**
     * <code>persistence.xml</code>のスキーマバージョンを返します。
     * @return <code>persistence.xml</code>のスキーマバージョン
     *
     * @since Java Persistence 2.0
     */
    public String getPersistenceXMLSchemaVersion();

    /**
     * Returns ClassLoader that the provider may use to load any
     * classes, resources, or open URLs.
     * @return ClassLoader that the provider may use to load any 
     * classes, resources, or open URLs 
     */
    public ClassLoader getClassLoader();

    /**
     * Add a transformer supplied by the provider that will be 
     * called for every new class definition or class redefinition
     * that gets loaded by the loader returned by the
     * {@link PersistenceUnitInfo#getClassLoader} method. The transformer 
     * has no effect on the result returned by the
     * {@link PersistenceUnitInfo#getNewTempClassLoader} method.
     * Classes are only transformed once within the same classloading
     * scope, regardless of how many persistence units they may be 
     * a part of.
     * @param transformer   provider-supplied transformer that the
     * container invokes at class-(re)definition time
     */
    public void addTransformer(ClassTransformer transformer);

    /**
     * Return a new instance of a ClassLoader that the provider may
     * use to temporarily load any classes, resources, or open
     * URLs. The scope and classpath of this loader is exactly the
     * same as that of the loader returned by {@link
     * PersistenceUnitInfo#getClassLoader}. None of the classes loaded
     * by this class loader will be visible to application
     * components. The provider may only use this ClassLoader within
     * the scope of the {@link
     * PersistenceProvider#createContainerEntityManagerFactory} call.
     * @return temporary ClassLoader with same visibility as current
     * loader
     */
    public ClassLoader getNewTempClassLoader();
}
