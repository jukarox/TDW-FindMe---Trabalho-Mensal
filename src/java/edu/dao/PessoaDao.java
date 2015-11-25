package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.exemplo.bean.Pessoa;

public class PessoaDao extends Dao {

    public Pessoa getPessoa(int id) {
        Connection conn = null;

        try {
            conn = getConnection();

            String sql = "SELECT * FROM Pessoa p WHERE p.Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getInt("Id"));
                pessoa.setNome(result.getString("Nome"));
                pessoa.setLocalDesaparecimento(result.getString("LocalDesaparecimento"));
                pessoa.setTelefoneContato(result.getString("TelefoneContato"));
                pessoa.setEncontrada(result.getBoolean("Encontrada"));
                
                return pessoa;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    //do nothing
                }
            }
        }
    }

    public List<Pessoa> getPessoasNaoEncontradas() {
        Connection conn = null;
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            conn = getConnection();

            String sql = "SELECT * FROM Pessoa p where p.Encontrada = false";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getInt("Id"));
                pessoa.setNome(result.getString("Nome"));
                pessoa.setLocalDesaparecimento(result.getString("LocalDesaparecimento"));
                pessoa.setTelefoneContato(result.getString("TelefoneContato"));
                pessoa.setEncontrada(result.getBoolean("Encontrada"));

                pessoas.add(pessoa);
            }

            return pessoas;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    //do nothing
                }
            }
        }
    }

    public boolean insertPessoa(Pessoa pessoa) {
        Connection conn = null;

        try {
            //obtem conexao com o banco de dados
            conn = getConnection();
            conn.setAutoCommit(false);

            //define SQL para inser��o
            String sql = "insert into Pessoa (Nome, LocalDesaparecimento, TelefoneContato, Encontrada) values (?, ?, ?, ?);";

            //instance Prepared statement especificando os par�metros do SQL
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getLocalDesaparecimento());
            stmt.setString(3, pessoa.getTelefoneContato());
            stmt.setBoolean(4, pessoa.isEncontrada());
            

            //executa a opera��o no banco de dados
            int affectedRows = stmt.executeUpdate();

            //verifica se deu certo. Se sim, obtem a chave id_aluno gerada 
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();

				//obtem a chave id_aluno no resultado da inser��o
                //1 indica primeira coluna no resultado vindo do banco de dados
                pessoa.setId(rs.getInt(1));
                
                //confirma as modifica��es no banco de dados
                conn.commit();
                return true;
            }
            //cancela as modifica��es no banco de dados
            conn.rollback();
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    //do nothing
                }
            }
        }
    }

    public boolean updatePessoa(Pessoa pessoa) {
        Connection conn = null;

        try {
            //obtem conexao com o banco de dados
            conn = getConnection();
            conn.setAutoCommit(false);

            //define SQL para atualiza��o
            String sql = "UPDATE Pessoa SET Nome=?, LocalDesaparecimento=?, TelefoneContato=?, Encontrada=? WHERE Id=?";

            //instance Prepared statement especificando os par�metros do SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getLocalDesaparecimento());
            stmt.setString(3, pessoa.getTelefoneContato());
            stmt.setBoolean(4, pessoa.isEncontrada());
            stmt.setInt(5, pessoa.getId());

            //executa a opera��o no banco de dados
            int affectedRows = stmt.executeUpdate();

            //verifica se deu certo. Se sim, atualiza a nota 
            if (affectedRows > 0) {
                conn.commit();
                return true;
            }

            //cancela as modifica��es no banco de dados
            conn.rollback();
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    //do nothing
                }
            }
        }
    }

    public boolean deletePessoa(int id) {
        Connection conn = null;

        try {
            //obtem conexao com o banco de dados
            conn = getConnection();

            //define SQL para atualiza��o
            String sql = "DELETE FROM Pessoa WHERE Id=?";

            //instance Prepared statement especificando os par�metros do SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            //executa a opera��o no banco de dados
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    //do nothing
                }
            }
        }
    }

}
