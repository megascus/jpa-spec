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
 package javax.persistence;

/**
 * 関連するエンティティに伝搬するカスケード操作の設定を定義します。
 * 
 * <code>cascade=ALL</code>は<code>cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH}</code>と同じ意味です。
 *
 * @since Java Persistence 1.0
 */
public enum CascadeType { 

    /** すべての操作をカスケードします。 */
    ALL, 

    /** 永続化操作をカスケードします。 */
    PERSIST, 

    /** マージ操作をカスケードします。 */
    MERGE, 

    /** 削除操作をカスケードします。 */
    REMOVE,

    /** リフレッシュ操作をカスケードします。 */
    REFRESH,

    /**
     * デタッチ操作をカスケードします。
     *
     * @since Java Persistence 2.0
     * 
     */   
    DETACH
}
