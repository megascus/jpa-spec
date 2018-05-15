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
     *  (<code>Subquery</code> <code>correlate</code>メソッドを使用して)相関の結果として取得された相関した<code>From</code>オブジェクトから親の<code>From</code>オブジェクトを返します。
     *  @return  相関したFromオブジェクトの親
     *  @throws IllegalStateException このFromオブジェクトが相関によって取得されていない場合
     */
    From<Z, X> getCorrelationParent();

    /**
     *  指定された単一の値の属性への内部結合を作成します。
     *  @param attribute  結合のターゲット
     *  @return 結果の結合
     */
    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute);

    /**
     *  指定された単一の値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param attribute  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt);

    /**
     *  指定されたCollectionの値の属性への内部結合を作成します。
     *  @param collection  結合のターゲット
     *  @return 結果の結合
     */
    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection);

    /**
     *  指定されたSetの値の属性への内部結合を作成します。
     *  @param set  結合のターゲット
     *  @return 結果の結合
     */
    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set);

    /**
     *  指定されたListの値の属性への内部結合を作成します。
     *  @param list  結合のターゲット
     *  @return 結果の結合
     */
    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list);

    /**
     *  指定されたMapの値の属性への内部結合を作成します。
     *  @param map  結合のターゲット
     *  @return 結果の結合
     */
    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map);

    /**
     *  指定されたCollectionの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param collection  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt);

    /**
     *  指定されたSetの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param set  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt);

    /**
     *  指定されたListの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param list  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt);

    /**
     *  指定されたMapの値の属性への与えられた結合の型を使用した結合を作成します。
     *  @param map  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt);

	
    //String-based:

    /**
     *  指定された属性への内部結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> Join<X, Y> join(String attributeName);	

    /**
     *  指定されたCollectionの値の属性への内部結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> CollectionJoin<X, Y> joinCollection(String attributeName);	

    /**
     *  指定されたSetの値の属性への内部結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> SetJoin<X, Y> joinSet(String attributeName);	

    /**
     *  指定されたListの値の属性への内部結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果の結合
     *  @throws IllegalArgumentException 与えられた名前の属性が存在しない場合
     */
    <X, Y> ListJoin<X, Y> joinList(String attributeName);		
    
    /**
     *  指定されたMapの値の属性への内部結合を作成します。
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
