/*******************************************************************************
 * Copyright (c) 2008 - 2017 Oracle Corporation. All rights reserved.
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
 * 結合の3つの型を定義します。
 *
 * 右外部結合と右外部フェッチ結合のサポートは必須ではありません。
 * <code>RIGHT</code>結合型を使用したアプリケーションは可搬的ではありません。
 *
 * @since Java Persistence 2.0
 */
public enum JoinType {

    /** 内部結合。 */
    INNER, 

    /** 左外部結合。 */
    LEFT, 

    /** 右外部結合。 */
    RIGHT
}
