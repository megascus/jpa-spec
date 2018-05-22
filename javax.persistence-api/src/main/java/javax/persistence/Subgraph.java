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

import javax.persistence.metamodel.Attribute;
import java.util.List;

/**
 * この型はマネージドタイプに対応する属性ノードのサブグラフを表します。
 * 
 * このクラスを使用する事でEntityGraph内にエンティティサブグラフを埋め込むことができます。
 *
 * @param <T> 属性の型
 *
 * @see EntityGraph
 * @see AttributeNode
 * @see NamedSubgraph
 *
 * @since Java Persistence 2.1
 */
public interface Subgraph<T> {

    /**
     * エンティティグラフに1つ以上の属性ノードを追加します。
     *
     * @param attributeName  属性の名前     
     * @throws IllegalArgumentException この属性がこのマネージドタイプの属性でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public void addAttributeNodes(String ... attributeName);

    /**
     * エンティティグラフに1つ以上の属性ノードを追加します。
     * @param attribute  属性
     *
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public void addAttributeNodes(Attribute<T, ?> ... attribute);

    /**
     * マネージドタイプに対応するノードをグラフに追加します。
     * 
     * これにより関連するマネージドタイプを含むマルチノードのエンティティグラフの構築が可能になります。
     *
     * @param attribute  属性
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(Attribute<T, X> attribute);

    /**
     * 継承のあるマネージドタイプに対応するノードをグラフに追加します。
     * 
     * これによりエンティティグラフのこのノードに対して複数のサブクラスのsubgraphを定義することを可能になります。
     * サブクラスのsubgraphには指定されたスーパークラスのsubgraphの属性が自動的に含まれます。
     *
     * @param attribute  属性
     * @param type  エンティティのサブクラス
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<? extends X> addSubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * マネージドタイプに対応するノードをグラフに追加します。
     * 
     * これにより関連するマネージドタイプを含むマルチノードのエンティティグラフの構築が可能になります。
     *
     * @param attributeName  属性の名前
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException この属性がこのマネージドタイプの属性でない場合
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(String attributeName);

    /**
     * 継承のあるマネージドタイプに対応するノードをグラフに追加します。
     * 
     * これによりエンティティグラフのこのノードに対して複数のサブクラスのsubgraphを定義することを可能になります。
     * サブクラスのsubgraphには指定されたスーパークラスのsubgraphの属性が自動的に含まれます。
     *
     * @param attributeName  属性の名前
     * @param type  エンティティのサブクラス
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException この属性がこのマネージドタイプの属性でない場合
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(String attributeName, Class<X> type);

    /**
     * マネージドタイプであるマップキーに対応するノードをグラフに追加します。
     * 
     * これにより関連するマネージドタイプを含むマルチノードのエンティティグラフの構築が可能になります。
     *
     * @param attribute  属性
     * @return キー属性のためのsubgraph
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプエンティティでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(Attribute<T, X> attribute);

    /**
     * 継承を持つマネージドタイプであるマップキーに対応するノードをグラフに追加します。
     * 
     * これによりエンティティグラフのこのノードに対して複数のサブクラスのsubgraphを定義することを可能になります。
     * サブクラスのsubgraphには指定されたスーパークラスのsubgraphの属性が自動的に含まれます。
     *
     * @param attribute  属性
     * @param type  エンティティのサブクラス
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプエンティティでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<? extends X> addKeySubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * マネージドタイプであるマップキーに対応するノードをグラフに追加します。
     * 
     * これにより関連するマネージドタイプを含むマルチノードのエンティティグラフの構築が可能になります。
     *
     * @param attributeName  属性の名前
     * @return キー属性のためのsubgraph
     * @throws IllegalArgumentException この属性がこのエンティティの属性でない場合
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(String attributeName);

    /**
     * 継承を持つマネージドタイプであるマップキーに対応するノードをグラフに追加します。
     * 
     * これによりエンティティグラフのこのノードに対して複数のサブクラスのsubgraphを定義することを可能になります。
     * サブクラスのsubgraphには指定されたスーパークラスのsubgraphの属性が自動的に含まれます。
     *
     * @param attributeName  属性の名前
     * @param type  エンティティのサブクラス
     * @return 属性のためのsubgraph
     * @throws IllegalArgumentException 属性がこのエンティティの属性でない場合
     * @throws IllegalArgumentException この属性のターゲットタイプがマネージドタイプでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(String attributeName, Class<X> type);

    /**
     * subgraphに含まれるこのマネージドタイプの属性に対応する属性ノードを返します。
     * @return subgraphに含まれる属性ノードのリスト、定義されていない場合は空のリスト
     */
    public List<AttributeNode<?>> getAttributeNodes();

    /**
     * このsubgraphが定義された型を返します。
     * @return subgraphによって参照されるマネージドタイプ
     */
    public Class<T> getClassType();
}
