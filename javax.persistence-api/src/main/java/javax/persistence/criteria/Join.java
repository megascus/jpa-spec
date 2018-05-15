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

import javax.persistence.metamodel.Attribute;

/**
 * エンティティや組み込み型、基本形への結合です。
 *
 * @param <Z> 結合のソースの型
 * @param <X> 結合のターゲットの型
 *
 * @since Java Persistence 2.0
 */
public interface Join<Z, X> extends From<Z, X> {

    /**
     *  結合を変更して、指定されたON条件に従って結果を制限する結合オブジェクトを返します。
     * 
     *  以前のON条件があれば置き換えます。
     *  @param restriction  単純な、もしくは複合したブール式
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    Join<Z, X> on(Expression<Boolean> restriction);

    /**
     *  結合を変更して、指定されたON条件に従って結果を制限する結合オブジェクトを返します。
     * 
     *  以前のON条件があれば置き換えます。
     *  @param restrictions  0個以上の制限述語
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    Join<Z, X> on(Predicate... restrictions);

    /** 
     *  結合のON制約に対応する述語、もしくはON条件が指定されていない場合はnullを返します。
     *  @return ON制約の述語
     *  @since Java Persistence 2.1
     */
    Predicate getOn();

    /**
     * この結合に対応するメタモデル属性を返します。
     * @return この結合に対応するメタモデル属性
     */
    Attribute<? super Z, ?> getAttribute();

    /**
     * 結合の親を返します。
     * @return 結合の親
     */
    From<?, Z> getParent();

    /**
     * 結合の型を返します。
     * @return 結合の型
     */
    JoinType getJoinType();
}
