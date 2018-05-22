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
 * プロバイダが永続化ユニットにレベル2のキャッシュを使用する方法を指定します。
 * 
 * <code>persistence.xml</code>の<code>shared-cache-mode</code>要素の値に対応し、
 * {@link javax.persistence.spi.PersistenceUnitInfo#getSharedCacheMode()}の結果として返されます。
 * 
 * @since Java Persistence 2.0
 */
public enum SharedCacheMode {

    /**
     * すべてのエンティティとエンティティ関連の状態とデータがキャッシュされます。
     */
    ALL, 

    /**
     * 永続化ユニットのキャッシュは無効です。
     */
    NONE, 

    /**
     * <code>Cacheable(true)</code>が指定されているすべてのエンティティでキャッシュが有効になっています。
     * 
     * 他のすべてのエンティティはキャッシュされません。
     */
    ENABLE_SELECTIVE, 

    /**
     * <code>Cacheable(false)</code>が指定されていないすべてのエンティティでキャッシュが有効になります。 
     * 
     * <code>Cacheable(false)</code>が指定されているエンティティはキャッシュされません。
     */
    DISABLE_SELECTIVE, 

    /**
     * キャッシュの動作は定義されていません。プロバイダ指定のデフォルトが適用される可能性があります。
     */
    UNSPECIFIED
}
