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

import java.security.ProtectionDomain;
import java.lang.instrument.IllegalClassFormatException;

/**
 * 永続性プロバイダーはこのインタフェースのインスタンスを{@link PersistenceUnitInfo#addTransformer PersistenceUnitInfo.addTransformer}メソッドに提供します。
 * 
 * 提供されたトランスフォーマーインスタンスはエンティティクラスのファイルがロードまたは再定義されたときに変換するために呼び出されます。
 * 変換はクラスがJVMによって定義される前に行われます。
 *
 * @since Java Persistence 1.0
 */
public interface ClassTransformer {

    /**
     * クラスがロードまたは再定義されるときに呼び出されます。
     * 
     * このメソッドの実装は与えられたクラスファイルを変換し、新しい変換されたクラスファイルを返すことがあります。
     *
     * @param loader  変換されるクラスの定義されたローダー、ブートストラップローダーの場合はnullでも良い
     * @param className  内部形式の完全修飾されたクラスおよびインタフェース名のクラスの名前
     * @param classBeingRedefined  再定義の場合は再定義されるクラス、そうでない場合はnull
     * @param protectionDomain  定義または再定義されるクラスの保護ドメイン
     * @param classfileBuffer  クラスファイル形式の入力バイトバッファー - 変換してはならない
     * @return (変換の結果)正常な形式のクラスファイルのバッファー、変換が行われなかった場合はnull
     * @throws IllegalClassFormatException  入力が正常な形式のクラスファイルを表していない場合
     */
    byte[] transform(ClassLoader loader,
                     String className,
                     Class<?> classBeingRedefined,
                     ProtectionDomain protectionDomain, 
                     byte[] classfileBuffer) 
        throws IllegalClassFormatException;
}
