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
     * (このアクセスは属性ステートのロードをトリガーしてもよいプロバイダーが<code>isLoadedWithoutReference</code>によって既に測定されているため安全です。)
     *
     * @param entity エンティティのインスタンス
     * @param attributeName  ロード状態を測定される属性の名前
     * @return 属性のロード状態
     */
    public LoadState isLoadedWithReference(Object entity, String attributeName);

    /**
     * エンティティがそれ自身により提供されており、<code>FetchType.EAGER</code>が指定されているすべての属性がロードされている状態だとプロバイダが判断した場合、
     * このメソッドは<code>LoadState.LOADED</code>を返します。
     * 
     * <p> エンティティがそれ自身により提供されており、<code>FetchType.EAGER</code>を指定された属性の一つでもロードされていないとプロバイダが判断した場合、
     * このメソッドは<code>LoadState.NOT_LOADED</code>を返します。
     * 
     * <p> プロバイダがエンティティがそれ自身により提供されているかどうかを判断できない場合、このメソッドは<code>LoadState.UNKNOWN</code>を返します。
     * 
     * <p> エンティティが別のプロバイダーによって提供された場合、エンティティの状態のローディングをトリガーしてしまう可能性があるため、
     * このメソッドのプロバイダの実装では、任意の属性値への参照を取得してはなりません。
     * @param entity ロード状態を測定されるエンティティ
     * @return エンティティのロード状態
     */
    public LoadState isLoaded(Object entity);
}
