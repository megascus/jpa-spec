/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/

package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <code>NamedAttributeNode</code>は<code>NamedEntityGraph</code>のメンバー属性です。
 *
 * @see NamedEntityGraph
 * @see NamedSubgraph
 *
 * @since Java Persistence 2.1
 */
@Target({})
@Retention(RUNTIME)
public @interface NamedAttributeNode {

    /**
     * (必須) グラフに含まれなければいけない属性の名前。
     */
    String value();

    /**
     * (オプション) 属性が独自のAttributeNodeを持つマネージドタイプ(エンティティ等)を参照する場合、
     * この要素はそのNamedSubgraphの定義を参照するために使用されます。
     * 
     * そのマネージドタイプが継承されたクラスを持つ場合は、複数のサブグラフを指定できます。
     * これらの追加のサブグラフは、サブクラス固有の属性を追加することを意図しています。
     * スーパークラスのサブグラフ項目はサブクラスのサブグラフにマージされます。
     * 
     * <p> この要素の値は<code>NamedSubgraph</code>要素に対応する<code>name</code>要素によって指定されたサブグラフの名前(name)です。
     * 複数のサブグラフが継承のために指定されている場合、それらはこの名前で参照されます。
     */
    String subgraph() default "";

   /**
    * (オプション) 属性がMap型を参照する場合、エンティティキータイプの場合にこの要素を使用してキーのサブグラフを指定できます。
    *
    * keySubgraphはMap属性が指定されていない場合は指定できません。
    * そのマネージドタイプが継承されたクラスを持つ場合は、複数のサブグラフを指定できます。
    * これらの追加のサブグラフは、サブクラス固有の属性を追加することを意図しています。
    * スーパークラスのサブグラフ項目はサブクラスのサブグラフにマージされます。
    * 
    * <p> この要素の値は<code>NamedSubgraph</code>要素に対応する<code>name</code>要素によって指定されたサブグラフの名前(name)です。
    * 複数のサブグラフが継承のために指定されている場合、それらはこの名前で参照されます。
    */
    String keySubgraph() default "";
}


