package Src.Controller;

import Src.Interface.Gerenciavel;
import Src.Model.Cliente;
import Src.Util.Logger;

import java.util.ArrayList;
import java.util.List;

public class ClienteController implements Gerenciavel<Cliente> {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        if (buscarPorId(cliente.getCPF()) != null) {
            return false; // Cliente já existe
        }
        clientes.add(cliente);
        Logger.log("Cliente cadastrado" + cliente.getNome() + "(CPF: " + cliente.getCPF() + ")");
        return true;
    }

    @Override
    public Cliente buscarPorId(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCPF().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public boolean atualizar(String cpf, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(cpf);
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setRendaMensal(clienteAtualizado.getRendaMensal());
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(String cpf) {
        return clientes.removeIf(c -> c.getCPF().equals(cpf));
    }
}
