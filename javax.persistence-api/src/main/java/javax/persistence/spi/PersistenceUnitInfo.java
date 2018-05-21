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
     * Returns a list of URLs for the jar files or exploded jar
     * file directories that the 永続化プロバイダ must examine
     * for managed classes of the persistence unit. Each URL
     * corresponds to a <code>jar-file</code> element in the
     * <code>persistence.xml</code> file. A URL will either be a 
     * file: URL referring to a jar file or referring to a directory
     * that contains an exploded jar file, or some other URL from
     * which an InputStream in jar format can be obtained.
     * @return a list of URL objects referring to jar files or
     * directories 
     */
    public List<URL> getJarFileUrls();

    /**
     * Returns the URL for the jar file or directory that is the
     * root of the persistence unit. (If the persistence unit is
     * rooted in the WEB-INF/classes directory, this will be the 
     * URL of that directory.)
     * The URL will either be a file: URL referring to a jar file 
     * or referring to a directory that contains an exploded jar
     * file, or some other URL from which an InputStream in jar
     * format can be obtained.
     * @return a URL referring to a jar file or directory
     */
    public URL getPersistenceUnitRootUrl();

    /**
     * Returns the list of the names of the classes that the
     * 永続化プロバイダ must add to its set of managed
     * classes. Each name corresponds to a named <code>class</code> element in the
     * <code>persistence.xml</code> file.
     * @return the list of the names of the classes that the 
     * 永続化プロバイダ must add to its set of managed 
     * classes 
     */
    public List<String> getManagedClassNames();

    /**
     * Returns whether classes in the root of the persistence unit
     * that have not been explicitly listed are to be included in the
     * set of managed classes. This value corresponds to the
     * <code>exclude-unlisted-classes</code> element in the <code>persistence.xml</code> file.
     * @return whether classes in the root of the persistence
     * unit that have not been explicitly listed are to be
     * included in the set of managed classes
     */
    public boolean excludeUnlistedClasses();

    /**
     * Returns the specification of how the provider must use
     * a second-level cache for the persistence unit.
     * The result of this method corresponds to the <code>shared-cache-mode</code>
     * element in the <code>persistence.xml</code> file.
     * @return the second-level cache mode that must be used by the
     * provider for the persistence unit
     *
     * @since Java Persistence 2.0
     */
    public SharedCacheMode getSharedCacheMode();

    /**
     * Returns the validation mode to be used by the persistence
     * provider for the persistence unit.  The validation mode
     * corresponds to the <code>validation-mode</code> element in the
     * <code>persistence.xml</code> file.
     * @return the validation mode to be used by the 
     * 永続化プロバイダ for the persistence unit
     * 
     * @since Java Persistence 2.0
     */
    public ValidationMode getValidationMode();

    /**
     * Returns a properties object. Each property corresponds to a
     * <code>property</code> element in the <code>persistence.xml</code> file
     * or to a property set by the container.
     * @return Properties object 
     */
    public Properties getProperties();
    
    /**
     * Returns the schema version of the <code>persistence.xml</code> file.
     * @return persistence.xml schema version
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
