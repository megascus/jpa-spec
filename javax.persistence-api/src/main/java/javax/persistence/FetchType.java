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

/**
 * データベースからデータをフェッチする戦略を定義します。
 * 
 * <code>EAGER</code>戦略は永続化プロバイダは実行時にデータをイーガーフェッチしなければならないという必須要件です。
 * <code>LAZY</code>戦略は永続化プロバイダは実行時にデータへの初回アクセス時にデータをレイジーフェッチするべきであるというヒントです。
 * 実装は<code>LAZY</code>戦略のヒントが指定されているデータでもイーガーフェッチすることが許されています。
 *
 * <pre>
 *   Example:
 *   &#064;Basic(fetch=LAZY)
 *   protected String getName() { return name; }
 * </pre>
 *
 * @see Basic
 * @see ElementCollection
 * @see ManyToMany
 * @see OneToMany
 * @see ManyToOne
 * @see OneToOne
 * @since Java Persistence 1.0
 */
public enum FetchType {

    /** データをレイジーフェッチすることができるという定義。 */
    LAZY,

    /** データをイーガーフェッチしなければならないという定義。 */
    EAGER
}
