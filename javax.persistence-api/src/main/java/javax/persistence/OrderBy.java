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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * コレクション値の関連もしくはコレクションが取得された時点でのそれらの順序を指定します。
 * 
 * <p> <code>orderby_list</code>の<code>value</code>順序要素の構文は以下の通りです。
 * 
 * <pre>
 *    orderby_list::= orderby_item [,orderby_item]*
 *    orderby_item::= [property_or_field_name] [ASC | DESC]
 * </pre>
 * 
 * <p> <code>ASC</code>も<code>DESC</code>も指定されていない場合、<code>ASC</code> (昇順)が指定されます。
 *
 * <p> エンティティの関連に順序要素が指定されていない場合、関連したエンティティの主キーによる順序付けが想定されます。
 *
 * <p> プロパティーまたはフィールド名は、関連するクラスまたはその内部の組み込みクラスの永続化プロパティーまたはフィールドの名前に対応する必要があります。
 * 順序付けで使用されるプロパティーまたはフィールドは、比較演算子がサポートされるカラムに対応していなければなりません。
 *
 * <p> ドット(".")表記は、組み込み属性内の属性を参照するために使用されます。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドまたはプロパティの名前です。
 *
 * <p> <code>OrderBy</code>アノテーションは要素コレクションに適用することができます。
 * <code>OrderBy</code>が基本型の要素コレクションに適用された場合、順序付けは基本型の値で行われ、プロパティーやフィールド名は使用されないでしょう。
 * 順序付けが組み込み型の要素コレクションに順序付けを指定する時には、順序を決定する属性を指定するためにドット表記を使用する必要があります。
 *
 * <code>OrderBy</code>アノテーションは<code>OrderColumn</code>が指定されている場合は使用しません。
 *
 * 
 * <pre>
 *    Example 1:
 *    
 *    &#064;Entity 
 *    public class Course {
 *       ...
 *       &#064;ManyToMany
 *       &#064;OrderBy("lastname ASC")
 *       public List&#060;Student&#062; getStudents() {...};
 *       ...
 *    }
 *    
 *    Example 2:
 *
 *    &#064;Entity 
 *    public class Student {
 *       ...
 *       &#064;ManyToMany(mappedBy="students")
 *       &#064;OrderBy // ordering by primary key is assumed
 *       public List&#060;Course&#062; getCourses() {...};
 *       ...
 *    }
 *
 *    Example 3: 
 *
 *    &#064;Entity 
 *    public class Person {
 *         ...
 *       &#064;ElementCollection
 *       &#064;OrderBy("zipcode.zip, zipcode.plusFour")
 *       public Set&#060;Address&#062; getResidences() {...};
 *       ...
 *    }
 *  
 *    &#064;Embeddable 
 *    public class Address {
 *       protected String street;
 *       protected String city;
 *       protected String state;
 *       &#064;Embedded protected Zipcode zipcode;
 *    }
 *
 *    &#064;Embeddable 
 *    public class Zipcode {
 *       protected String zip;
 *       protected String plusFour;
 *    }
 * </pre>
 *
 * @see OrderColumn
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface OrderBy {

   /**
    * <code>orderby_list</code>。
    *
    * 以下のように指定します。
    *
    * <pre>
    *    orderby_list::= orderby_item [,orderby_item]*
    *    orderby_item::= [property_or_field_name] [ASC | DESC]
    * </pre>
    *
    * <p> <code>ASC</code>も<code>DESC</code>も指定されていない場合、<code>ASC</code> (昇順)が指定されます。
    *
    * <p> 順序要素が指定されていない場合、関連したエンティティの主キーによる順序付けが想定されます。
    */
    String value() default "";
}
