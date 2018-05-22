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

import java.util.List;
import javax.persistence.metamodel.ListAttribute;

/**
 * <code>ListJoin</code>インターフェイスは<code>java.util.List</code>として指定された関連や要素コレクションを介してコレクションに結合した結果の型です。
 *
 * @param <Z> 結合のソースの型
 * @param <E> ターゲット<code>List</code>の要素型
 *
 * @since Java Persistence 2.0
 */
public interface ListJoin<Z, E> 
		extends PluralJoin<Z, List<E>, E> {

    /**
     * 結合を変更して指定されたON条件に従って結果を制限した結合オブジェクトを戻します。
     * 
     * 以前のON条件があれば置き換えます。
     *  @param restriction  単純な、もしくは複合したブール式
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    ListJoin<Z, E> on(Expression<Boolean> restriction);

    /**
     * 結合を変更して指定されたON条件に従って結果を制限した結合オブジェクトを戻します。
     * 
     * 以前のON条件があれば置き換えます。
     *  @param restrictions  0個以上の制限の述語
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    ListJoin<Z, E> on(Predicate... restrictions);

    /**
     * リスト属性のメタモデル表現を返します。
     * @return 結合のターゲットである<code>List</code>を表すメタモデル型
     */
    ListAttribute<? super Z, E> getModel();

    /**
     * 参照された関連付けまたは要素コレクション内のオブジェクトのインデックスに対応する式を作ります。
     * 
     * このメソッドは順序列が定義されている関連付けまたは要素コレクションを表すオブジェクトに対してのみ呼び出される必要があります。
     * @return インデックスを表す式
     */
    Expression<Integer> index();
}
