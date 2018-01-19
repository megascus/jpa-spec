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

/**
 * エンティティの属性の状態をデータベースの列表現に変換して元に戻すためにこのインタフェースを実装するクラスを使用できます。
 * 
 * XとYの型は同じJavaの型である可能性があることに注意してください。
 *
 * @param <X>  エンティティの属性の型
 * @param <Y>  データベースの列の型
 */
public interface AttributeConverter<X,Y> {

    /**
     * エンティティの属性に格納されている値をデータ表現に変換してデータベースに格納します。
     *
     * @param attribute  変換されるエンティティの属性値
     * @return  データベースの列に格納するために変換されたデータ
     */
    public Y convertToDatabaseColumn (X attribute);

    /**
     * データベース列のに格納されたデータをエンティティの属性に格納される値に変換します。
     * 
     * JDBCドライバによって使用される対応する列の<code>dbData</code>の正しい型を指定するのはコンバーターライターの責任であることに注意してください。
     * つまり、永続化プロバイダがそのような型変換を行うことは期待されません。
     *
     * @param dbData  変換されるデータベース列からのデータ
     * @return  エンティティの属性に格納するために変換された値
     */
    public X convertToEntityAttribute (Y dbData);
}
