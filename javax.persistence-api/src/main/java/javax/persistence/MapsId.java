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


import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link EmbeddedId}主キーもしくは<code>EmbeddedId</code>主キー内の属性、単純な親エンティティの主キーによるマッピングを提供する<code>ManyToOne</code>または<code>OneToOne</code>リレーションシップの属性を指定します。
 * 
 * <code>value</code>要素はリレーションシップの属性が対応する複合キー内の属性を指定します。
 * エンティティの主キーがリレーションシップによって参照されるエンティティの主キーと同じJavaの型である場合、value属性は指定されません。
 * 
 * <pre>
 *    Example:
 *
 *    // 親エンティティが単純な主キーを持つ場合
 *
 *    &#064;Entity
 *    public class Employee {
 *       &#064;Id long empId;
 *       String name;
 *       ...
 *    } 
 *
 *    // 依存するエンティティが複合キーのためにEmbeddedIdを使用している場合
 *
 *    &#064;Embeddable
 *    public class DependentId {
 *       String name;
 *       long empid;   // Employeeの主キーの型に対応
 *    }
 *
 *    &#064;Entity
 *    public class Dependent {
 *       &#064;EmbeddedId DependentId id;
 *        ...
 *       &#064;MapsId("empid")  //  組み込まれたID(EmbeddedId)の属性のempidにマッピング
 *       &#064;ManyToOne Employee emp;
 *    }
 * </pre>
 *
 * @since Java Persistence 2.0
 */
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface MapsId {

    /**
     * (オプション) リレーションシップの属性に関連する複合キーに含まれる属性の名前。
     * 
     * 指定されない場合にはリレーションシップはエンティティの主キーでマッピングされます。
     */
   String value() default ""; }
