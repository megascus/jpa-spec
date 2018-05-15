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

import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.MapAttribute;

/**
 * 境界型やコレクションからの単純な、もしくは複合した属性のパスを表す"プリミティブ"な式です。
 *
 * @param <X>  パスによって参照される型
 *
 * @since Java Persistence 2.0
 */
public interface Path<X> extends Expression<X> {

    /** 
     * パス式に対応する束縛可能なオブジェクトを返します。
     * @return パス式に対応する束縛可能なオブジェクト
     */
    Bindable<X> getModel(); 
    
    /**
     * パスの親"ノード"を返します。親が存在しない場合はnullです。
     *  @return 親
     */
    Path<?> getParentPath();
	
    /**
     * 参照された単独の値を持つ属性に対応するパスを作ります。
     *  @param attribute 単独の値持つ属性
     *  @return 参照された属性に対応するパス
     */
    <Y> Path<Y> get(SingularAttribute<? super X, Y> attribute);

    /**
     * 参照されたコレクション値の属性に対応するパスを作ります。
     *  @param collection コレクション値の属性
     *  @return 参照された属性に対応するパス
     */
    <E, C extends java.util.Collection<E>> Expression<C> get(PluralAttribute<X, C, E> collection);

    /**
     *  参照されたマップ値の属性に対応するパスを作ります。
     *  @param map マップ値の属性
     *  @return 参照された属性に対応するパス
     */
    <K, V, M extends java.util.Map<K, V>> Expression<M> get(MapAttribute<X, K, V> map);

    /**
     *  パスの型に対応する式を作ります。
     *  @return パスの型に対応する式
     */
    Expression<Class<? extends X>> type();


    //String-based:
	
    /**
     *  参照された属性に対応するパスを作ります。
     * 
     *  <p> 注意：文字列ベースのAPIを使用するアプリケーションでは<code>Path</code>変数の使用を避けるために
     * <code>get</code>操作の結果の型を指定する必要があります。
     *
     *  <pre>
     *     良い例:
     *
     *     CriteriaQuery&#060;Person&#062; q = cb.createQuery(Person.class);
     *     Root&#060;Person&#062; p = q.from(Person.class);
     *     q.select(p)
     *      .where(cb.isMember("joe",
     *                         p.&#060;Set&#060;String&#062;&#062;get("nicknames")));
     *
     *     良くない例:
     * 
     *     CriteriaQuery&#060;Person&#062; q = cb.createQuery(Person.class);
     *     Root&#060;Person&#062; p = q.from(Person.class);
     *     Path&#060;Set&#060;String&#062;&#062; nicknames = p.get("nicknames");
     *     q.select(p)
     *      .where(cb.isMember("joe", nicknames));
     *  </pre>
     *
     *  @param attributeName  属性の名前
     *  @return 参照された属性に対応するパス
     *  @throws IllegalStateException 基本型に対応するパスで呼び出された場合
     *  @throws IllegalArgumentException 指定された名前の属性が存在しない場合
     */
    <Y> Path<Y> get(String attributeName);
}
