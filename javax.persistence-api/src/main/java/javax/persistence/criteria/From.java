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
package javax.persistence.criteria;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import java.util.Set;

/**
 * 境界型を現します。通常、from句に表されるエンティティですが、from句のエンティティに属する組み込み型の可能性もあります。
 * 
 * <p> 関連や組み込み、型に属するコレクションの結合のファクトリー及びに型に属する属性へのパスとして機能します。
 *
 * @param <Z>  ソースの型
 * @param <X>  ターゲットの型
 *
 * @since Java Persistence 2.0
 */
@SuppressWarnings("hiding")
public interface From<Z, X> extends Path<X>, FetchParent<Z, X> {

    /**
     *  この境界型から作られた結合を返します。
     * 
     *  この境界型から結合が作られていない場合は空のセットを返します。
     *  返されたセットへの変更はクエリーには影響を与えません。
     *  @return この型から作られた結合
     */
    Set<Join<X, ?>> getJoins();
	
    /**
     *  このFromオブジェクトが(<code>Subquery</code> <code>correlate</code>メソッドを使用して)相関の結果として取得されたかどうか。
     *  @return このオブジェクトが相関によって取得されたかを示すブール値
     */
    boolean isCorrelated();

    /**
     *  Returns the parent <code>From</code> object from which the correlated
     *  <code>From</code> object has been obtained through correlation (use
     *  of a <code>Subquery</code> <code>correlate</code> method).
     *  @return  the parent of the correlated From object
     *  @throws IllegalStateException if the From object has
     *          not been obtained through correlation 
     */
    From<Z, X> getCorrelationParent();

    /**
     *  Create an inner join to the specified single-valued 
     *  attribute.
     *  @param attribute  結合のターゲット
     *  @return 結果の結合
     */
    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute);

    /**
     *  Create a join to the specified single-valued attribute 
     *  using the given join type.
     *  @param attribute  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt);

    /**
     *  Create an inner join to the specified Collection-valued 
     *  attribute.
     *  @param collection  結合のターゲット
     *  @return 結果の結合
     */
    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection);

    /**
     *  Create an inner join to the specified Set-valued attribute.
     *  @param set  結合のターゲット
     *  @return 結果の結合
     */
    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set);

    /**
     *  Create an inner join to the specified List-valued attribute.
     *  @param list  結合のターゲット
     *  @return 結果の結合
     */
    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list);

    /**
     *  Create an inner join to the specified Map-valued attribute.
     *  @param map  結合のターゲット
     *  @return 結果の結合
     */
    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map);

    /**
     *  Create a join to the specified Collection-valued attribute 
     *  using the given join type.
     *  @param collection  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt);

    /**
     *  Create a join to the specified Set-valued attribute using 
     *  the given join type.
     *  @param set  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt);

    /**
     *  Create a join to the specified List-valued attribute using 
     *  the given join type.
     *  @param list  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt);

    /**
     *  Create a join to the specified Map-valued attribute using 
     *  the given join type.
     *  @param map  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt);

	
    //String-based:

    /**
     *  Create an inner join to the specified attribute.
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> Join<X, Y> join(String attributeName);	

    /**
     *  Create an inner join to the specified Collection-valued 
     *  attribute.
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName);	

    /**
     *  Create an inner join to the specified Set-valued attribute.
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> SetJoin<X, Y> joinSet(String attributeName);	

    /**
     *  Create an inner join to the specified List-valued attribute.
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> ListJoin<X, Y> joinList(String attributeName);		
    
    /**
     *  Create an inner join to the specified Map-valued attribute.
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, K, V> MapJoin<X, K, V> joinMap(String attributeName);	

    /**
     *  指定された属性への与えられた結合の型を使用した結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合の型
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> Join<X, Y> join(String attributeName, JoinType jt);	
    
    /**
     *  指定されたCollectionの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合の型
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName, JoinType jt);	

    /**
     *  指定されたSetの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合の型
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> SetJoin<X, Y> joinSet(String attributeName, JoinType jt);	

    /**
     *  指定されたListの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合の型
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> ListJoin<X, Y> joinList(String attributeName, JoinType jt);	

    /**
     *  指定されたMapの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合のターゲット
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, K, V> MapJoin<X, K, V> joinMap(String attributeName, JoinType jt);	
}
