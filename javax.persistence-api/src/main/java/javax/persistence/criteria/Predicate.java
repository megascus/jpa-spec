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

/**
 * 単一述語または複合述語の型、制限の結合または分離です。
 * 
 * 単一述語は、単一の結合詞との結合であるとみなされます。
 *
 * @since Java Persistence 2.0
 */
public interface Predicate extends Expression<Boolean> {

        public static enum BooleanOperator {
              AND, OR
        }
	
    /**
     * 述語のブール演算子を返します。
     * 
     * 述語が単一である場合は<code>AND</code>を返します。
     * @return 述語のブール演算子
     */
    BooleanOperator getOperator();
    
    /**
     * この述語がほかの述語から<code>Predicate.not()</code>メソッドや<code>CriteriaBuilder.not()</code>メソッドを使用して作られたかどうか。
     * @return 述語が述語の否定であるかを示すブール値
     */
    boolean isNegated();

    /**
     * 述語の最上位の結合または分離を返します。
     * 
     * 述語に最上位の結合または分離がない場合は空のリストを返します。
     * リストへの変更はクエリーに影響しません。
     * @return 述語を構成するブール式のリスト
     */
    List<Expression<Boolean>> getExpressions();

    /**
     * 述語の否定を作ります。
     * @return 否定の述語
     */
    Predicate not();

}
