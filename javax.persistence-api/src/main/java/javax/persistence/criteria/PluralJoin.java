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

/**
 * <code>PluralJoin</code>インターフェースはすべてのコレクション型への結合に共通の機能を定義します。
 * 
 * これはクエリーの構築に直接使用するためのものではありません。
 *
 * @param <Z> ソースの型
 * @param <C> コレクションの型
 * @param <E> コレクションの要素の型 
 *
 * @since Java Persistence 2.0
 */
public interface PluralJoin<Z, C, E> extends Join<Z, E> {

    /**
     * 結合に対応するコレクションの値を持つ属性を表すメタモデルを返します。
     * @return 結合に対応するコレクションの値を持つ属性を表すメタモデル
     */
    PluralAttribute<? super Z, C, E> getModel();
}
