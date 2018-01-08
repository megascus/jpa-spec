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
     * Return the name of a named EntityGraph (an entity graph
     * defined by means of the <code>NamedEntityGraph</code>
     * annotation, XML descriptor element, or added by means of the
     * <code>addNamedEntityGraph</code> method.  Returns null if the
     * EntityGraph is not a named EntityGraph.
     */
    public String getName();

    /**
     * エンティティグラフに一つ以上の属性ノードを追加します。
     *
     * @param attributeName  属性の名前
     * @throws IllegalArgumentException if the attribute is not an 
     *         attribute of this entity.
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
     */
    public void addAttributeNodes(String ... attributeName);

    /**
     * Add one or more attribute nodes to the entity graph.
     *
     * @param attribute  属性
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
     */
    public void addAttributeNodes(Attribute<T, ?> ... attribute);

    /**
     * Add a node to the graph that corresponds to a managed
     * type. This allows for construction of multi-node entity graphs
     * that include related managed types.  
     *
     * @param attribute  属性
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not a managed type
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
     */
    public <X> Subgraph<X> addSubgraph(Attribute<T, X> attribute);

    /**
     * Add a node to the graph that corresponds to a managed
     * type with inheritance.  This allows for multiple subclass
     * subgraphs to be defined for this node of the entity
     * graph. Subclass subgraphs will automatically include the
     * specified attributes of superclass subgraphs. 
     *
     * @param attribute  属性
     * @param type  エンティティのサブクラス
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException if the attribute's target 
     *         type is not a managed type
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
     */
    public <X> Subgraph<? extends X> addSubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * Add a node to the graph that corresponds to a managed
     * type. This allows for construction of multi-node entity graphs
     * that include related managed types.  
     *
     * @param attributeName  属性の名前
     * @return 属性のためのサブグラフ
     * @throws IllegalArgumentException if the attribute is not an 
     *         attribute of this entity.
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not a managed type
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
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
     * @throws IllegalArgumentException if the attribute is not an 
     *         attribute of this managed type.
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not a managed type
     * @throws IllegalStateException if this EntityGraph has been
     *         statically defined
     */
    public <X> Subgraph<X> addSubgraph(String attributeName, Class<X> type);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type. This allows for construction of
     * multi-node entity graphs that include related managed types.
     *
     * @param attribute  属性
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not an entity
     * @throws IllegalStateException if this EntityGraph has been 
     *         statically defined
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
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not an entity
     * @throws IllegalStateException if this EntityGraph has been 
     *         statically defined
     */
    public <X> Subgraph<? extends X> addKeySubgraph(Attribute<T, X> attribute, Class<? extends X> type);

    /**
     * Add a node to the graph that corresponds to a map key
     * that is a managed type. This allows for construction of
     * multi-node entity graphs that include related managed types.
     *
     * @param attributeName  属性の名前
     * @return キー属性のためのサブグラフ
     * @throws IllegalArgumentException if the attribute is not an 
     *         attribute of this entity.
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not an entity
     * @throws IllegalStateException if this EntityGraph has been
     *          statically defined
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
     * @throws IllegalArgumentException if the attribute is not an 
     *         attribute of this entity.
     * @throws IllegalArgumentException if the attribute's target type 
     *         is not a managed type
     * @throws IllegalStateException if this EntityGraph has been 
     *         statically defined
     */
    public <X> Subgraph<X> addKeySubgraph(String attributeName, Class<X> type);


    /**
     * Add additional attributes to this entity graph that
     * correspond to attributes of subclasses of this EntityGraph's
     * entity type.  Subclass subgraphs will automatically include the
     * specified attributes of superclass subgraphs.
     *
     * @param type  エンティティのサブクラス
     * @return サブクラスのためのサブグラフ
     * @throws IllegalArgumentException if the type is not an entity type
     * @throws IllegalStateException if the EntityGraph has been 
     *         statically defined
     */
    public <T> Subgraph<? extends T> addSubclassSubgraph(Class<? extends T> type);


    /**
     * Return the attribute nodes of this entity that are included in
     * the entity graph.
     * @return attribute nodes for the annotated entity type or empty
     *         list if none have been defined
     */
    public List<AttributeNode<?>> getAttributeNodes();

}
