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

import javax.persistence.TupleElement;
import java.util.List;

/**
 * <code>Selection</code>インターフェースはクエリーの結果から返されるだろう項目を定義します。
 *
 * @param <X> 選択項目の型
 *
 * @since Java Persistence 2.0
 */
public interface Selection<X> extends TupleElement<X> {

    /**
     * 選択項目にエイリアスを割り当てます。
     * 
     * 一度割り当てると、変更や再割り当てはできません。
     * 同じ選択項目を返します。
     * @param name  エイリアス
     * @return 選択項目 
     */
    Selection<X> alias(String name);

    /**
     * 選択項目が複合選択かどうかを返します。
     * @return 選択項目が複合選択項目かどうかを示すブール値
     */
    boolean isCompoundSelection();

    /**
     * 複合選択を構成する選択項目を返します。
     * 
     * リストへの変更はクエリーに影響しません。
     * @return 選択項目のリスト
     * @throws IllegalStateException このオブジェクトが複合選択でない場合
     */
    List<Selection<?>> getCompoundSelectionItems();
}
