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
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <code>NamedSubgraph</code>は<code>NamedEntityGraph</code>のメンバー要素です。
 * 
 * <code>NamedSubgraph</code>はNamedEntityGraph内からのみ参照され、単独で参照することはできません。
 * <code>NamedSubgraph</code>は<code>NamedEntityGraph</code>の<code>NamedAttributeNode</code>要素からその<code>name</code>で参照されます。
 * 
 * @see NamedEntityGraph
 * @see NamedAttributeNode
 *
 * @since Java Persistence 2.1
 */
@Target({})
@Retention(RUNTIME)
public @interface NamedSubgraph {

    /**
     * (必須) NamedAttributeNode要素から参照されるサブグラフの名前。
     */
    String name();

    /**
     * (オプション) このサブグラフによって表される型。
     * 
     * このサブグラフがサブクラスに代わって定義を拡張しているときは要素を指定する必要があります。
     */
    Class type() default void.class;

    /** 
     * (必須) 含まなければならないクラスの属性のリスト。
     * 
     * 名前付きサブグラフが対応する属性ノードによって参照されるクラスのサブクラスに対応する場合、サブクラス固有の属性のみがリストされます。
     */
    NamedAttributeNode[] attributeNodes();
}


