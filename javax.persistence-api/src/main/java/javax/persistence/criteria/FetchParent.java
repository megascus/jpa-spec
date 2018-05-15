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

/**
 * Fetchの親として機能するfrom句の要素を表します。
 *
 * @param <Z>  元の型
 * @param <X>  対象の型
 *
 * @since Java Persistence 2.0
 */
public interface FetchParent<Z, X> {

    /**
     *  この型から作成されたフェッチ結合を返します。
     * 
     *  この型からフェッチ結合が行われていない場合は空のセットを返します。
     *  セットへの変更はクエリに影響しません。
     *  @return この型から作成されたフェッチ結合
     */
    java.util.Set<Fetch<X, ?>> getFetches();

    /**
     *  指定された単一値属性への内部結合を使用したフェッチ結合を作成します。
     *  @param attribute  結合のターゲット
     *  @return 結果のフェッチ結合
     */	
    <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute);

    /**
     *  指定された単一値属性への与えられた結合の型を使用したフェッチ結合を作成します。
     *  @param attribute  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果のフェッチ結合
     */	
    <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt);

    /**
     *  指定されたコレクション値の属性への内部結合を使用したフェッチ結合を作成します。
     *  @param attribute  結合のターゲット
     *  @return 結果の結合
     */
    <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute);
	
    /**
     *  指定されたコレクション値の属性への与えられた結合の型を使用したフェッチ結合を作成します。
     *  @param attribute  結合のターゲット
     *  @param jt  結合の型
     *  @return 結果の結合
     */
    <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute, JoinType jt);
	

    //String-based:
	
    /**
     *  指定された属性への内部結合を使用したフェッチ結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @return 結果のフェッチ結合
     *  @throws IllegalArgumentException 与えられた属性の名前が存在しない場合
     */	
    @SuppressWarnings("hiding")
    <X, Y> Fetch<X, Y> fetch(String attributeName);

    /**
     *  指定された属性への与えられた結合の型を使用したフェッチ結合を作成します。
     *  @param attributeName  結合のターゲットの属性の名前
     *  @param jt  結合の型
     *  @return 結果のフェッチ結合
     *  @throws IllegalArgumentException 与えられた属性の名前が存在しない場合
     */	
    @SuppressWarnings("hiding")
    <X, Y> Fetch<X, Y> fetch(String attributeName, JoinType jt);
}
