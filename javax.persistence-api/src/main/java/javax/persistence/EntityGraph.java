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
 * この型はエンティティグラフとエンティティ関係の境界と属性ノードを定義するためのテンプレートとして使用されるエンティティグラフのルートを表します。
 * 
 * ルートはエンティティ型でなければなりません。
 * <p>
 * サブグラフを追加するメソッドは暗黙的に対応する属性ノードも作成します。
 * そのような属性ノードは重複して指定するべきではありません。
 *
 * @param <T> ルートエンティティの型
 *
 * @see AttributeNode
 * @see Subgraph
 * @see NamedEntityGraph
 *
 * @since Java Persistence 2.1
 */
public interface EntityGraph<T> {

    /**
     * 名前付きEntityGraph(<code>NamedEntityGraph</code>アノテーション、XMLディスクリプタ要素で定義された、または<code>addNamedEntityGraph</code>メソッドで追加されたエンティティグラフ)の名前を返します。
     * 
     * EntityGraphが名前付きEntityGraphでない場合はnullを返します。
     */
    public String getName();

    /**
     * エンティティグラフに一つ以上の属性ノードを追加します。
     *
     * @param attributeName  属性の名前
     * @throws IllegalArgumentException 属性がこのエンティティの属性でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public void addAttributeNodes(String ... attributeName);

    /**
     * エンティティグラフに一つ以上の属性ノードを追加します。
     *
     * @param attribute  属性
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public void addAttributeNodes(Attribute<T, ?> ... attribute);

    /**
     * 管理された型に対応するノードをグラフに追加します。
     * 
     * これにより関連する管理対象タイプを含むマルチノードのエンティティグラフの構築が可能になります。
     *
     * @param attribute  属性
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException 属性のターゲットタイプが管理された型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(Attribute<T, X> attribute);

    /**
     * 継承のある管理された型に対応するノードをグラフに追加します。
     * 
     * これによりエンティティグラフのこのノードに対して複数のサブクラスのサブグラフを定義することが可能になります。
     * サブクラスのサブグラフには指定されたスーパークラスのサブグラフの属性が自動的に含まれます。
     *
     * @param attribute  属性
     * @param type  エンティティのサブクラス
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException 属性のターゲットタイプが管理された型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<? extends X> addSubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * Add a node to the graph that corresponds to a managed
     * type. This allows for construction of multi-node entity graphs
     * that include related managed types.  
     *
     * @param attributeName  属性の名前
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException 属性がこのエンティティの属性でない場合
     * @throws IllegalArgumentException 属性のターゲットタイプが管理された型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(String attributeName);

    /**
     * Add a node to the graph that corresponds to a managed
     * type with inheritance.  This allows for multiple subclass
     * subgraphs to be defined for this node of the entity graph.
     * Subclass subgraphs will automatically include the specified
     * attributes of superclass subgraphs.  
     *
     * @param attributeName  属性の名前
     * @param type  エンティティのサブクラス
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException 属性がこの管理された型の属性でない場合
     * @throws IllegalArgumentException 属性のターゲットタイプが管理された型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addSubgraph(String attributeName, Class<X> type);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type. This allows for construction of
     * multi-node entity graphs that include related managed types.
     *
     * @param attribute  属性
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException 属性のターゲットタイプがエンティティでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(Attribute<T, X> attribute);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type with inheritance. This allows for
     * construction of multi-node entity graphs that include related
     * managed types.  Subclass subgraphs will include the specified
     * attributes of superclass subgraphs.
     *
     * @param attribute  属性
     * @param type  エンティティのサブクラス
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException 属性のターゲットタイプがエンティティでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<? extends X> addKeySubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type. This allows for construction of
     * multi-node entity graphs that include related managed types.
     *
     * @param attributeName  属性の名前
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException 属性がこのエンティティの属性でない場合
     * @throws IllegalArgumentException 属性のターゲットタイプがエンティティでない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(String attributeName);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type with inheritance. This allows for
     * construction of multi-node entity graphs that include related
     * managed types. Subclass subgraphs will automatically include
     * the specified attributes of superclass subgraphs
     *
     * @param attributeName  属性の名前
     * @param type  エンティティのサブクラス
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException 属性がこのエンティティの属性でない場合
     * @throws IllegalArgumentException 属性のターゲットタイプが管理された型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <X> Subgraph<X> addKeySubgraph(String attributeName, Class<X> type);


    /**
     * このEntityGraphのエンティティ型のサブクラスの属性に対応する追加の属性をこのエンティティグラフに追加します。
     * 
     * サブクラスのサブグラフには指定されたスーパークラスのサブグラフの属性が自動的に含まれます。
     *
     * @param type  エンティティのサブクラス
     * @return サブクラスのためのサブグラフ
     * @throws IllegalArgumentException typeがエンティティ型でない場合
     * @throws IllegalStateException EntityGraphが静的に定義されている場合
     */
    public <T> Subgraph<? extends T> addSubclassSubgraph(Class<? extends T> type);


    /**
     * エンティティグラフに含まれるこのエンティティの属性ノードを返します。
     * @returnアノテーションの付いたエンティティ型のための属性ノード、何も定義されてない場合は空のList
     */
    public List<AttributeNode<?>> getAttributeNodes();

}
