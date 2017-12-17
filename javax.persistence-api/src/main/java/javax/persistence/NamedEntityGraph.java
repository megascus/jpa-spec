/*******************************************************************************
 * Copyright (c) 2011 - 2015 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *
 ******************************************************************************/

package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 検索操作やクエリーのパスと境界を指定するために使用されます。
 *
 * @since Java Persistence 2.1
 */
@Repeatable(NamedEntityGraphs.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface NamedEntityGraph {

    /**
     * (オプション) エンティティグラフの名前。
     * デフォルトはルートエンティティのエンティティ名です。
     */
    String name() default "";

    /**
     * (オプション) このグラフに含まれるエンティティの属性のリスト。
     */
    NamedAttributeNode[] attributeNodes() default {};

    /**
     * (オプション) アノテーションの付いたエンティティクラスのすべての属性を明示的にNamedEntityGraphの属性ノードとして一覧化する必要なしに含めます。
     * 
     * 含められた属性はサブグラフを参照する属性ノードによって完全に指定することができます。
     */
    boolean includeAllAttributes() default false;

    /**
     * (オプション) エンティティグラフに含まれるサブグラフのリスト。
     * 
     * これらは、NamedAttributeNode定義から名前で参照されます。
     */
    NamedSubgraph[] subgraphs() default {};

    /**
     * (オプション) アノテーションの付いたエンティティクラスのサブクラスの追加の属性をエンティティグラフに追加するサブグラフのリスト。
     * 
     * スーパークラスの指定された属性はサブクラスに含まれます。
     */
    NamedSubgraph[] subclassSubgraphs() default {};
}

