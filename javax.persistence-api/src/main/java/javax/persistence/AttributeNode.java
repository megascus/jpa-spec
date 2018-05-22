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

import java.util.Map;

/**
 * エンティティグラフの属性ノードを表します。
 *
 * @param <T> 属性の型
 *
 * @see EntityGraph
 * @see Subgraph
 * @see NamedAttributeNode
 *
 * @since Java Persistence 2.1
 */
public interface AttributeNode<T> {

    /**
     * 属性ノードに対応する属性の名前を返します。
     * @return 属性の名前
     */
    public String getAttributeName();

    /**
     * この属性ノードに関連する部分グラフのMap&#060;Class, Subgraph&#062;を返します。
     * @return この属性ノードに関連付けられた部分グラフのマップ、または定義されていない場合は空のマップ
     */
    public Map<Class, Subgraph> getSubgraphs();

    /**
     * この属性ノードのマップキーに関連付けられたサブグラフのMap&#060;Class, Subgraph&#062;を返します。
     * @return この属性ノードのマップキーに関連付けられたサブグラフのマップ、または定義されていない場合は空のマップ
     */
    public Map<Class, Subgraph> getKeySubgraphs();
}

