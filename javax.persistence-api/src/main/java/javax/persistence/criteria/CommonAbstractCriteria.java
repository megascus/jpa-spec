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

/**
 * <code>CommonAbstractCriteria</code>インターフェースはトップレベルのクライテリアクエリーとサブクエリーの両方だけではなく更新と削除のクライテリア処理にも同様に共通な機能を定義します。
 * これはクエリーの構築に直接使用するためのものではありません。
 *
 * <p> クライテリアクエリーとクライテリア更新およびクライテリア削除操作の入力方法は異なることに注意してください。
 * クライテリアクエリーはクエリー結果型に従って入力されます。
 * 更新または削除操作は更新または削除の対象に応じて入力されます。
 *
 * @since Java Persistence 2.1
 */
public interface CommonAbstractCriteria {

    /**
     * クエリーのサブクエリーを作ります。
     * @param type  サブクエリーの結果型
     * @return サブクエリー
     */
    <U> Subquery<U> subquery(Class<U> type);

    /**
     * where句の制限に対応する述語を返します。
     * 
     * 制限が指定されていない場合はnullを返します。
     * @return where句述語
     */
    Predicate getRestriction();
 
}
