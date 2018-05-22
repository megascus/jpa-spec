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
 * フェッチ結合(JOIN FETCH)された関連もしくは属性を表します。
 *
 * @param <Z>  フェッチの元の型
 * @param <X>  フェッチの対象の型
 *
 * @since Java Persistence 2.0
 */
public interface Fetch<Z, X> extends FetchParent<Z, X> {

    /**
     * フェッチ結合に対応するメタモデル属性を返します。
     * @return 結合のメタモデル属性
     */
    Attribute<? super Z, ?> getAttribute();

    /**
     * フェッチされた項目の親を返します。
     * @return フェッチ親
     */
    FetchParent<?, Z> getParent();

    /**
     * フェッチ結合に使用される結合型を返します。
     * @return 結合型
     */
    JoinType getJoinType();
}
