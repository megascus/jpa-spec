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

import java.util.List;

/**
 * 実行環境で利用可能な永続化プロバイダのリストを測定します。
 * 
 * <p> 実装はスレッドセーフでなければなりません。
 * 
 *  <p> <code>getPersistenceProviders</code>メソッドは何度も呼び出される可能性があることに注意してください。
 * このメソッドの実装ではキャッシュを活用することをお勧めします。
 *
 * @see PersistenceProvider
 * @since Java Persistence 2.0
 */
public interface PersistenceProviderResolver {

    /**
     * 実行環境で利用可能な<code>PersistenceProvider</code>の実装のリストを返します。
     *
     * @return 実行環境で利用可能な永続化プロバイダのリスト
     */
    List<PersistenceProvider> getPersistenceProviders();

    /**
     * プロバイダのキャッシュをクリアします。
     *
     */
    void clearCachedProviders();
} 
