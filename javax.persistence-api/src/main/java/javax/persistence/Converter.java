/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * アノテーションの付けられたクラスがコンバーターであることを指定し、そのスコープを定義します。
 * 
 * コンバータークラスには<code>Converter</code>アノテーションをつけるか、コンバーターとしてORMディスクリプターで定義する必要があります。
 * 
 * <p><code>autoApply</code>要素が<code>true</code>として指定されている場合、
 * <code>Convert</code>アノテーション(もしくはXMLで同等の設定)で変換が上書きされた属性を除いて
 * 永続化ユニット内のすべてのエンティティの指定されたターゲット型のすべてのマッピングされた属性に対して永続化プロバイダは自動的にコンバーターを適用する必要があります。
 * 
 * <p>コンバーターが属性に適用可能かどうかを判断するためにプロバイダはプリミティブ型とラッパー型を同等に扱う必要があります。
 * 
 * <p>Id属性、バージョン属性、リレーションシップの属性、および<code>Enumerated</code>もしくは<code>Temporal</code>アノテーションが明確に付けられた(もしくはXMLで指定された)属性は変換されないことに注意してください。
 * 
 * <p><code>autoApply</code>が<code>true</code>の場合、<code>Convert</code>アノテーションを使用して属性ごとに自動適用変換を無効または無効にできるに注意してください。
 * 
 * <p><code>autoApply</code>が<code>false</code>の場合、<code>Convert</code>アノテーション(または対応するXML要素)が指定されているターゲットタイプの属性のみが変換されます。
 * 
 * <p>同じターゲットタイプに対して複数のコンバーターが定義されている場合、 <code>Convert</code>アノテーションは使用するコンバーターを明示的に指定する必要があります。
 *
 * @see AttributeConverter
 * @see Convert
 *
 * @since Java Persistence 2.1
 */
@Target({TYPE}) @Retention(RUNTIME)
public @interface Converter {
     boolean autoApply() default false;
}
