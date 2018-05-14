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

import java.util.Collection;

/**
 * クエリーの式を表す型です。
 *
 * @param <T> 式の型
 *
 * @since Java Persistence 2.0
 */
public interface Expression<T> extends Selection<T> {

    /**
     *  式がnullかどうかを検証する述語を作成します。
     *  @return 式がnullかどうかを検証する述語
     */
    Predicate isNull();

    /**
     *  式がnullでないかどうかを検証する述語を作成します。
     *  @return 式がnullでないかどうかを検証する述語
     */
    Predicate isNotNull();

    /**
     * 式が引数のリストに含まれるかどうかを検証する述語を作成します。
     * @param values  検証される値
     * @return 含まれることを検証する述語
     */
    Predicate in(Object... values);

    /**
     * 式が引数のリストに含まれるかどうかを検証する述語を作成します。
     * @param values  検証される式
     * @return 含まれることを検証する述語
     */
    Predicate in(Expression<?>... values);

    /**
     * 式が集合に含まれるかどうかを検証する述語を作成します。
     * @param values  検証される値の集合
     * @return 含まれることを検証する述語
     */
    Predicate in(Collection<?> values);

    /**
     * 式が集合に含まれるかどうかを検証する述語を作成します。
     * @param values 検証される集合に対応する式
     * @return 含まれることを検証する述語
     */
    Predicate in(Expression<Collection<?>> values);

    /**
     * Perform a typecast upon the expression, returning a new
     * expression object.
     * This method does not cause type conversion:
     * the runtime type is not changed.
     * Warning: may result in a runtime failure.
     * @param type  intended type of the expression
     * @return new expression of the given type
     */
    <X> Expression<X> as(Class<X> type);
}
