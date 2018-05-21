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
package javax.persistence.spi;

/**
 * 永続化プロバイダによって実装されるユーティリティインタフェース。
 * 
 * このインタフェースは{@link javax.persistence.PersistenceUtil}の実装によって呼び出され、
 * エンティティまたはエンティティの属性のロード状態を測定します。
 *
 * @since Java Persistence 2.0
 */
public interface ProviderUtil { 

    /**
     * エンティティがそれ自身により提供されており、指定された属性がロードされている状態だとプロバイダが測定した場合、
     * このメソッドは<code>LoadState.LOADED</code>を返します。
     * 
     * <p> エンティティがそれ自身により提供されており、<code>FetchType.EAGER</code>を持つエンティティの属性がロードされていないか、
     * 指定された属性がロードされていない状態だとプロバイダが測定した場合、このメソッドは<code>LoadState.NOT_LOADED</code>を返します。
     * 
     * <p> プロバイダがロード状態を測定できない場合、このメソッドは<code>LoadState.UNKNOWN</code>を返します。
     * 
     * <p> エンティティが別のプロバイダーによって提供された場合、エンティティの状態のローディングをトリガーしてしまう可能性があるため、
     * このメソッドのプロバイダの実装では、属性値への参照を取得してはなりません。
     * @param entity エンティティのインスタンス
     * @param attributeName  ロード状態を測定される属性の名前
     * @return 属性のロード状態
     */
    public LoadState isLoadedWithoutReference(Object entity, String attributeName);

    /**
     * If the provider determines that the entity has been provided
     * by itself and that the state of the specified attribute has
     * been loaded, this method returns <code>LoadState.LOADED</code>.
     * <p> If a provider determines that the entity has been provided
     * by itself and that either the entity attributes with <code>FetchType.EAGER</code>
     * have not been loaded or that the state of the specified
     * attribute has not been loaded, this method returns
     * return <code>LoadState.NOT_LOADED</code>.
     * <p> If the provider cannot determine the load state, this method
     * returns <code>LoadState.UNKNOWN</code>.
     * <p> The provider's implementation of this method is permitted to
     * obtain a reference to the attribute value.  (This access is
     * safe because providers which might trigger the loading of the
     * attribute state will have already been determined by
     * <code>isLoadedWithoutReference</code>. )
     *
     * @param entity エンティティのインスタンス
     * @param attributeName  ロード状態を測定される属性の名前
     * @return 属性のロード状態
     */
    public LoadState isLoadedWithReference(Object entity, String attributeName);

    /**
     * If the provider determines that the entity has been provided
     * by itself and that the state of all attributes for which
     * <code>FetchType.EAGER</code> has been specified have been loaded, this 
     * method returns <code>LoadState.LOADED</code>.
     * <p> If the provider determines that the entity has been provided
     * by itself and that not all attributes with <code>FetchType.EAGER</code> 
     * have been loaded, this method returns <code>LoadState.NOT_LOADED</code>.
     * <p> If the provider cannot determine if the entity has been
     * provided by itself, this method returns <code>LoadState.UNKNOWN</code>.
     * <p> The provider's implementation of this method must not obtain
     * a reference to any attribute value, as this could trigger the
     * loading of entity state if the entity has been provided by a
     * different provider.
     * @param entity ロード状態を測定されるエンティティ
     * @return エンティティのロード状態
     */
    public LoadState isLoaded(Object entity);
}
